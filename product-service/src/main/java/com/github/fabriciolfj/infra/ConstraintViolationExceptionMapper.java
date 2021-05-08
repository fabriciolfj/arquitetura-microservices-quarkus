package com.github.fabriciolfj.infra;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) {
        final var validacoes = ConstraintViolationResponse.of(e);
        return Response.status(Response.Status.BAD_REQUEST).entity(validacoes).build();
    }
}
