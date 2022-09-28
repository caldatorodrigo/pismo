package com.pismo.backtest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pismo.backtest.model.DTO.TransactionsDTO;
import com.pismo.backtest.service.TransactionService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Transactions", description = "description transactions the account")
@RestController
public class TransactionControle {
	private TransactionService transactionService;

	public TransactionControle(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/api/transactions")
	ResponseEntity<Object> save(@RequestBody TransactionsDTO transactionDto) {
		return transactionService.save(transactionDto);
	}

}