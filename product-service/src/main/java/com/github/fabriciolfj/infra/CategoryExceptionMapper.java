package com.github.fabriciolfj.infra;

import com.github.fabriciolfj.domain.exceptions.CategoryException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CategoryExceptionMapper implements ExceptionMapper<CategoryException> {

    @Override
    public Response toResponse(CategoryException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(ErroReponse.builder().message(e.getMessage()).build())
                .build();
    }
}
