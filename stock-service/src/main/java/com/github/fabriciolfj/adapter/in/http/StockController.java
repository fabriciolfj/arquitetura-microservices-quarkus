package com.github.fabriciolfj.adapter.in.http;

import com.github.fabriciolfj.adapter.in.mapper.StockMapper;
import com.github.fabriciolfj.core.port.in.StockIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Slf4j
@Path("stocks")
@ApplicationScoped
@RequiredArgsConstructor
@Produces("application/json")
@Consumes("application/json")
public class StockController {

    private final StockIn stockIn;
    private final StockMapper stockMapper;

    @GET
    @Path("{code}")
    public Response find(@PathParam("code") final String code) {
        return Response.status(Response.Status.ACCEPTED).entity(
                stockMapper.toResponse(stockIn.getStock(code))).build();
    }
}
