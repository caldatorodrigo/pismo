package com.pismo.backtest.model.DTO;

import com.pismo.backtest.model.AccountsModel;

public class AccountsDTO {
    private String Document_Number;


    public String getDocument_Number() {
		return Document_Number;
	}


	public void setDocument_Number(String document_Number) {
		Document_Number = document_Number;
	}


	/** DTO only */
    public AccountsModel transformToObject(){
        return new AccountsModel(Document_Number);
    }
}
