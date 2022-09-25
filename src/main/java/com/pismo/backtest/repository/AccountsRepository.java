package com.pismo.backtest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pismo.backtest.model.AccountsModel;

@Repository
public interface AccountsRepository extends JpaRepository<AccountsModel, Long> {
	
	@Query("select u from Accounts u where u.Document_Number = ?1")
	List<AccountsModel> findByDocument_Number(String document_Number);
}

