package com.pismo.backtest.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.pismo.backtest.interfaces.TransactionServiceInterface;
import com.pismo.backtest.model.AccountsModel;
import com.pismo.backtest.model.OperationsTypesModel;
import com.pismo.backtest.model.TransactionsModel;
import com.pismo.backtest.model.DTO.TransactionsDTO;
import com.pismo.backtest.model.Error.ApiError;
import com.pismo.backtest.repository.AccountsRepository;
import com.pismo.backtest.repository.OperationsTypesRepository;
import com.pismo.backtest.repository.TransactionsRepository;

@Component
public class TransactionService implements TransactionServiceInterface {

	private TransactionsRepository transactionsRepository;
	private AccountsRepository accountsRepository;
	private OperationsTypesRepository operationsTypesRepository;

	@Autowired
	public TransactionService(TransactionsRepository transactionsRepository, AccountsRepository accountsRepository,
			OperationsTypesRepository operationsTypesRepository) {
		this.transactionsRepository = transactionsRepository;
		this.accountsRepository = accountsRepository;
		this.operationsTypesRepository = operationsTypesRepository;
	}

	@Override
	public ResponseEntity<Object> save(TransactionsDTO transactionDto) {
		ApiError error = validFields(transactionDto);
		if (error != null) {
			return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
		}
		;

		TransactionsModel transactionsModelNew = transactionsRepository.save(transactionDto.transformToObject());
		return new ResponseEntity<>(transactionsModelNew, HttpStatus.CREATED);
	}

	private Boolean validAmount(Integer OperationType, BigDecimal Amount) {
		Boolean amountResult = false;

		switch (OperationType) {
		case 4:
			if ((Amount.intValue()) > 0) {
				amountResult = true;
			} else {
				amountResult = false;
			}
			break;
		default:
			if (Amount.intValue() < 0) {
				amountResult = true;
			} else {
				amountResult = false;
			}
			break;
		}
		return amountResult;
	}

	private ApiError validFields(TransactionsDTO transactionDto) {
		List<String> errorList = new ArrayList<>();

		/** Check if account exists */
		Optional<AccountsModel> accountsModel = accountsRepository.findById(transactionDto.getAccount_ID());
		if (accountsModel == null) {
			errorList.add("001 :Account not found - Possibly the account was not created.");
		}

		/** Checks if the operation type exists */
		Optional<OperationsTypesModel> operationsTypesModel = operationsTypesRepository
				.findById(transactionDto.getOperationType_ID());
		if (operationsTypesModel == null) {
			errorList.add("002: Operation Type not found - Possibly type of operation not supported by the system.");
		}

		/** check payment record according to the type of operation */
		Boolean valid = validAmount(Long.valueOf(transactionDto.getOperationType_ID()).intValue(),
				transactionDto.getAmount());
		if (!valid) {
			errorList.add(
					"003: Amount outside the standard of the operation types - check the amount rules by type of operation.");
		}

		if (errorList.size() > 0) {
			return new ApiError(HttpStatus.BAD_REQUEST, "Error encountered while processing the transaction",
					errorList);
		} else {
			return null;
		}
	}

}
