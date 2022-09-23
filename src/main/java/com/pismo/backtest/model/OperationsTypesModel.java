package com.pismo.backtest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Operationstypes")
public class OperationsTypesModel {

    @Id
    @GeneratedValue
    private Long OperationsType_ID;
    private String Description;

    public Long getOperationsTypes_ID() {
        return OperationsType_ID;
    }
    public void setOperationsTypes_ID(Long operationsTypes_ID) {
        OperationsType_ID = operationsTypes_ID;
    }

    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
}
