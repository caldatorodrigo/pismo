package com.pismo.backtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pismo.backtest.model.TransactionsModel;

@Repository
public interface TransactionsRepository extends JpaRepository<TransactionsModel, Long> {}
