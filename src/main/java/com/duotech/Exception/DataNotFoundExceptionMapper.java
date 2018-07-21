package com.duotech.Exception;

import com.duotech.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

    @Override
    public Response toResponse(DataNotFoundException ex){
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "wwww.google.co.nz");
        return Response.status(Response.Status.NOT_FOUND).entity(errorMessage).build();
    }
}
