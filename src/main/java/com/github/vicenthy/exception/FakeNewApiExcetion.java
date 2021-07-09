package com.github.vicenthy.exception;

import javax.ws.rs.core.Response.Status;

public class FakeNewApiExcetion extends RuntimeException{
    


    private final ResponseError responseError = new ResponseError();

    public FakeNewApiExcetion(String msg, Status status) {
        super(msg);
        this.responseError.setMessage(msg);
        this.responseError.setStatus(status);
    }

    public ResponseError getResponseError() {
        return responseError;
    }
}
