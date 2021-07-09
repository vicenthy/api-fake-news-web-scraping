package com.github.vicenthy.exception;

import javax.ws.rs.core.Response.Status;

public class ResponseError {
    private String message;
    private Status status;



    
    public String getMessage() {
        return message;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}