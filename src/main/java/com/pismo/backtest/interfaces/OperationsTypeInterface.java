package com.pismo.backtest.interfaces;

import org.springframework.http.ResponseEntity;

import com.pismo.backtest.model.DTO.OperationsTypesDTO;

public interface OperationsTypeInterface {
	ResponseEntity<Object> save(OperationsTypesDTO operationsTypesDto);
}
