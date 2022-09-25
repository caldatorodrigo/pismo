package com.pismo.backtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pismo.backtest.model.OperationsTypesModel;
import com.pismo.backtest.model.DTO.OperationsTypesDTO;
import com.pismo.backtest.repository.OperationsTypesRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Operations Types", description = "description of Operations Types")
@RestController
public class OperationsTypesControler {
    
    private OperationsTypesRepository operationsTypesRepository;

    @Autowired
    public OperationsTypesControler(OperationsTypesRepository operationsTypesRepository) {
        this.operationsTypesRepository = operationsTypesRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/operationstipes/save")
    ResponseEntity<Object> save(@RequestBody OperationsTypesDTO operationsTypesDto) {
        OperationsTypesModel operationsTypesModel = operationsTypesRepository.save(operationsTypesDto.transformToObject());
        return new ResponseEntity<>(operationsTypesModel, HttpStatus.CREATED);
    }

}
