package com.pismo.backtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pismo.backtest.model.OperationsTypesModel;

@Repository
public interface OperationsTypesRepository extends JpaRepository<OperationsTypesModel, Long> {}
