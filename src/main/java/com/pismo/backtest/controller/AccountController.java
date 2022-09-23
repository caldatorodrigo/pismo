package com.pismo.backtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pismo.backtest.model.AccountsModel;
import com.pismo.backtest.repository.AccountsRepository;

@RestController
public class AccountController {
    private AccountsRepository accountRespository;

    @Autowired
    public AccountController(AccountsRepository accountRespository) {
        this.accountRespository = accountRespository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/account/save")
    AccountsModel save(@RequestBody AccountsModel account) {
        return accountRespository.save(account);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/account/{accountId}")
    AccountsModel userById(@PathVariable Long accountId) {
        return accountRespository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
