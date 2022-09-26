package com.pismo.backtest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.pismo.backtest.interfaces.AccountsServiceInterface;
import com.pismo.backtest.model.AccountsModel;
import com.pismo.backtest.model.DTO.AccountsDTO;
import com.pismo.backtest.model.Error.ApiError;
import com.pismo.backtest.repository.AccountsRepository;

@Component
public class AccountsService implements AccountsServiceInterface{
	private AccountsRepository accountsRepository;
	
	@Autowired
	public AccountsService(AccountsRepository accountsRepository) {
		this.accountsRepository = accountsRepository;
	}
    
	@Override
	public ResponseEntity<Object> save(AccountsDTO accountDto) {
    	ApiError error = validFields(accountDto);
    	if(error != null) {
    		return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
    	};
   	
    	AccountsModel accountModel = accountsRepository.save(accountDto.transformToObject());
        return new ResponseEntity<>(accountModel, HttpStatus.CREATED);
	}
	
	private ApiError validFields(AccountsDTO accountDto) {
		List<String> errorList = new ArrayList<>();
		List<AccountsModel> AccountsModelList= new ArrayList<>();
		
		/** Check if account exists by Documment_Number*/	
		AccountsModelList = accountsRepository.findByDocument_Number(accountDto.getDocument_Number());
        if(AccountsModelList.size() > 0){
        	errorList.add("004 :Account found - possibly the account has already been created.");
        }
        
        if(errorList.size() > 0) {
        	return new ApiError(HttpStatus.FOUND, "Error encountered while processing the account", errorList);
        }else {
        	return null;
        }
	}

	@Override
	public ResponseEntity<Object> loadById(Long accountId) {
        AccountsModel accountsModel = accountsRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(accountsModel, HttpStatus.FOUND);
	}
}
