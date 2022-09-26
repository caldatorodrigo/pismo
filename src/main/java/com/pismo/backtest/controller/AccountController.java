package com.pismo.backtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pismo.backtest.model.DTO.AccountsDTO;
import com.pismo.backtest.service.AccountsService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Account", description = "description of account")
@RestController
public class AccountController {
	private AccountsService accountsService;

	@Autowired
	public AccountController(AccountsService accountsService) {
		this.accountsService = accountsService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/api/account/save")
	ResponseEntity<Object> save(@RequestBody AccountsDTO accountDto) {
		return accountsService.save(accountDto);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/account/{accountId}")
	ResponseEntity<Object> userById(@PathVariable Long accountId) {
		return accountsService.loadById(accountId);
	}

}
