package com.pismo.backtest.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "Transactions")
public class TransactionsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Transaction_ID;
	@NotNull(message = "AccountID is mandatory!")
	private Long Account_ID;
	@NotNull(message = "OperationTypeID is mandatory!")
	private Long OperationType_ID;
	@NotNull(message = "Amount is mandatory!")
	private BigDecimal Amount;

	@Column(name = "`EventDate`")
	@CreationTimestamp
	private Date EventDate;

	/** Hibernate */
	public TransactionsModel() {
	}

	/** DTO only */
	public TransactionsModel(Long account_ID, Long operationType_ID, BigDecimal amount) {
		Account_ID = account_ID;
		OperationType_ID = operationType_ID;
		Amount = amount;
	}

	public Long getTransaction_ID() {
		return Transaction_ID;
	}

	public void setTransaction_ID(Long transaction_ID) {
		Transaction_ID = transaction_ID;
	}

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

	public Date getEventDate() {
		return EventDate;
	}

	public void setEventDate(Date eventDate) {
		EventDate = eventDate;
	}

}
