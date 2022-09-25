package com.pismo.backtest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity(name="Accounts")
public class AccountsModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Account_ID;

    @NotEmpty(message = "Document_Number is mandatory!")
    private String Document_Number;
        
    /** hibernate */
    public AccountsModel() {
    }

    /** DTO only*/
    public AccountsModel(@NotEmpty String document_Number) {
        Document_Number = document_Number;
    }

    public Long getAccount_ID() {
		return Account_ID;
	}

	public void setAccount_ID(Long account_ID) {
		Account_ID = account_ID;
	}

	public String getDocument_Number() {
		return Document_Number;
	}

	public void setDocument_Number(String document_Number) {
		Document_Number = document_Number;
	}

}



