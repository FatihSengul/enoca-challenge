package com.enoca.commerce.exception;

import lombok.Data;

@Data
public class ErrorResponse {

    private String message;

    private Integer status;

    public ErrorResponse(String message,int status) {
        this.message = message;
        this.status = status;
    }


    public void setMessage(String message) {
        this.message = message;
    }

}

