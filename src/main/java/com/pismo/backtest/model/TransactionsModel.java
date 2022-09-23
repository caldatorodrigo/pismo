package com.pismo.backtest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name="Transactions")
public class TransactionsModel {
    
    @Id
    @GeneratedValue
    private Long Transaction_ID;
    private Long Account_ID;

    private Long OperationType_ID;
    
    @Digits(integer = 10, fraction = 2)
    private Float Amount;
    
    @Column(name = "`EventDate`")
	@CreationTimestamp
	private Date EventDate;


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

    public Float getAmount() {
        return Amount;
    }

    public void setAmount(Float amount) {
        Amount = amount;
    }

}
