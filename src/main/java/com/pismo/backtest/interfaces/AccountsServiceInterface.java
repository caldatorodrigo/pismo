package com.pismo.backtest.interfaces;

import org.springframework.http.ResponseEntity;

import com.pismo.backtest.model.DTO.AccountsDTO;

public interface AccountsServiceInterface {
	ResponseEntity<Object> save(AccountsDTO accountDto);

	ResponseEntity<Object> loadById(Long accountId);
}
