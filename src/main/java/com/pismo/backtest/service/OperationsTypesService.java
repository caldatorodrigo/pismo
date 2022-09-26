package com.pismo.backtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.pismo.backtest.interfaces.OperationsTypeInterface;
import com.pismo.backtest.model.OperationsTypesModel;
import com.pismo.backtest.model.DTO.OperationsTypesDTO;
import com.pismo.backtest.repository.OperationsTypesRepository;

@Component
public class OperationsTypesService implements OperationsTypeInterface{
    private OperationsTypesRepository operationsTypesRepository;

    @Autowired
    public OperationsTypesService(OperationsTypesRepository operationsTypesRepository) {
        this.operationsTypesRepository = operationsTypesRepository;
    }
	
	@Override
	public ResponseEntity<Object> save(OperationsTypesDTO operationsTypesDto) {
        OperationsTypesModel operationsTypesModel = operationsTypesRepository.save(operationsTypesDto.transformToObject());
        return new ResponseEntity<>(operationsTypesModel, HttpStatus.CREATED);
	}

}
