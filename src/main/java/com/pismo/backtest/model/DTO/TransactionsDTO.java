package com.pismo.backtest.model.DTO;

import java.math.BigDecimal;

import com.pismo.backtest.model.TransactionsModel;

public class TransactionsDTO {
	private Long Account_ID;
	private Long OperationType_ID;
	private BigDecimal Amount;

	public Long getAccount_ID() {
		return Account_ID;
	}

	public void setAccount_ID(Long account_ID) {
		Account_ID = account_ID;
	}

	public Long getOperationType_ID() {
		return OperationType_ID;
	}

	public void setOperationType_ID(Long operationType_ID) {
		OperationType_ID = operationType_ID;
	}

	public BigDecimal getAmount() {
		return Amount;
	}

	public void setAmount(BigDecimal amount) {
		Amount = amount;
	}

	public TransactionsModel transformToObject() {
		return new TransactionsModel(Account_ID, OperationType_ID, Amount);
	}
}
