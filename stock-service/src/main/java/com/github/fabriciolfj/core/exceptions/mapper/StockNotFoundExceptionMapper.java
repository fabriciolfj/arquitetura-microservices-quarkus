package com.github.fabriciolfj.core.exceptions.mapper;

import com.github.fabriciolfj.core.exceptions.StockNotFoundException;
import com.github.fabriciolfj.core.exceptions.dto.ErrorDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class StockNotFoundExceptionMapper implements ExceptionMapper<StockNotFoundException> {

    @Override
    public Response toResponse(final StockNotFoundException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(ErrorDTO.builder()
                        .msg(e.getMessage())
                        .build())
                .build();
    }
}
