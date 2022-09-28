package com.pismo.backtest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.pismo.backtest.interfaces.OperationsTypeInterface;
import com.pismo.backtest.model.AccountsModel;
import com.pismo.backtest.model.OperationsTypesModel;
import com.pismo.backtest.model.DTO.AccountsDTO;
import com.pismo.backtest.model.DTO.OperationsTypesDTO;
import com.pismo.backtest.model.Error.ApiError;
import com.pismo.backtest.repository.OperationsTypesRepository;

@Component
public class OperationsTypesService implements OperationsTypeInterface {
	private OperationsTypesRepository operationsTypesRepository;

	@Autowired
	public OperationsTypesService(OperationsTypesRepository operationsTypesRepository) {
		this.operationsTypesRepository = operationsTypesRepository;
	}

	@Override
	public ResponseEntity<Object> save(OperationsTypesDTO operationsTypesDto) {
		ApiError error = validFields(operationsTypesDto);
		if (error != null) {
			return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
		}
		;
		
		OperationsTypesModel operationsTypesModel = operationsTypesRepository
				.save(operationsTypesDto.transformToObject());
		return new ResponseEntity<>(operationsTypesModel, HttpStatus.CREATED);
	}
	
	private ApiError validFields(OperationsTypesDTO operationsTypesDTO) {
		List<String> errorList = new ArrayList<>();

		/** Check if Document_number is null ou Blank */
		if(operationsTypesDTO.getDescription() == null || operationsTypesDTO.getDescription() == "") {
			errorList.add("006 :OperationsTypes field Description is mandatory!");
		}

		if (errorList.size() > 0) {
			return new ApiError(HttpStatus.BAD_REQUEST, "Error encountered while processing the account", errorList);
		} else {
			return null;
		}
	}


}
