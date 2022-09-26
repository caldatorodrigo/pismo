package com.pismo.backtest.model.DTO;

import com.pismo.backtest.model.OperationsTypesModel;

public class OperationsTypesDTO {
	private String Description;

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	/** DTO only */
	public OperationsTypesModel transformToObject() {
		return new OperationsTypesModel(Description);
	}

}
