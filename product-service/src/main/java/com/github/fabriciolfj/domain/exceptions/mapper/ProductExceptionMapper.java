package com.github.fabriciolfj.domain.exceptions.mapper;

import com.github.fabriciolfj.domain.exceptions.ProductException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ProductExceptionMapper implements ExceptionMapper<ProductException> {

    @Override
    public Response toResponse(ProductException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(ErroReponse.builder().message(e.getMessage()).build())
                .build();
    }
}
