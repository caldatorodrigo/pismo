package com.pismo.backtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pismo.backtest.model.AccountsModel;

@Repository
public interface AccountsRepository extends JpaRepository<AccountsModel, Long> {}

