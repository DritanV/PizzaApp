package com.homework.pizza_app.model.response;

/**
 *
 * @author dritan
 */
public class OperationStatusModel {

    private String OperationName;
    private String OperationResult;

    public OperationStatusModel() {
    }

    public String getOperationResult() {
        return OperationResult;
    }

    public void setOperationResult(String OperationResult) {
        this.OperationResult = OperationResult;
    }

    public String getOperationName() {
        return OperationName;
    }

    public void setOperationName(String OperationName) {
        this.OperationName = OperationName;
    }

}
