package com.pismo.backtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pismo.backtest.model.TransactionsModel;
import com.pismo.backtest.repository.TransactionsRepository;

@RestController
public class TransactionControle {
    private TransactionsRepository transactionsRepository;

    @Autowired
    public TransactionControle(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    @PostMapping("/transaction/save")
    TransactionsModel save(@RequestBody TransactionsModel transaction) {
        return transactionsRepository.save(transaction);
    }
}
