package com.pismo.backtest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pismo.backtest.model.AccountsModel;
import com.pismo.backtest.model.DTO.AccountsDTO;
import com.pismo.backtest.model.Error.ApiError;
import com.pismo.backtest.repository.AccountsRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Account", description = "description of account")
@RestController
public class AccountController {
    private AccountsRepository accountsRepository;

    @Autowired
    public AccountController(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/account/save")
    ResponseEntity<Object> save(@RequestBody AccountsDTO accountDto) {
        
    	ApiError error = validFields(accountDto);
    	if(error != null) {
    		return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
    	};
   	
    	AccountsModel accountModel = accountsRepository.save(accountDto.transformToObject());
        return new ResponseEntity<>(accountModel, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/account/{accountId}")
    ResponseEntity<AccountsModel> userById(@PathVariable Long accountId) {
        AccountsModel accountsModel = accountsRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(accountsModel, HttpStatus.FOUND);
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
   
    
}
