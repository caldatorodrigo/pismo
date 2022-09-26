package com.pismo.backtest.interfaces;

import org.springframework.http.ResponseEntity;

import com.pismo.backtest.model.DTO.TransactionsDTO;

public interface TransactionServiceInterface {
	ResponseEntity<Object> save(TransactionsDTO transactionDto);
}
