package com.pismo.backtest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="Operationstypes")
public class OperationsTypesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long OperationsType_ID;
    private String Description;

	public OperationsTypesModel() {
    }
    
    public OperationsTypesModel(String description) {
        Description = description;
    }

    public Long getOperationsType_ID() {
		return OperationsType_ID;
	}

	public void setOperationsType_ID(Long operationsType_ID) {
		OperationsType_ID = operationsType_ID;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
   
}
