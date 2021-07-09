package com.github.vicenthy.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class FakeNewApiExcetionHandler implements ExceptionMapper<FakeNewApiExcetion>{
   
    
    @Override
    public Response toResponse(FakeNewApiExcetion exception) {
        if (exception.getResponseError().getStatus() != null)
            return Response.status(exception.getResponseError().getStatus()).entity(exception.getResponseError()).build();
        else
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(exception.getResponseError()).build();
    }
}