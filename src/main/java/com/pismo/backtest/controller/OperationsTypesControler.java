package com.pismo.backtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pismo.backtest.model.DTO.OperationsTypesDTO;
import com.pismo.backtest.service.OperationsTypesService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Operations Types", description = "description of Operations Types")
@RestController
public class OperationsTypesControler {

	private OperationsTypesService operationsTypesService;

	@Autowired
	public OperationsTypesControler(OperationsTypesService operationsTypesService) {
		this.operationsTypesService = operationsTypesService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/api/operationstypes")
	ResponseEntity<Object> save(@RequestBody OperationsTypesDTO operationsTypesDto) {
		return operationsTypesService.save(operationsTypesDto);
	}

}
