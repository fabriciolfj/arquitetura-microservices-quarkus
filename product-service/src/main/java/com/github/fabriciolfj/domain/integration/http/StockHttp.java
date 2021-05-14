package com.github.fabriciolfj.domain.integration.http;

import com.github.fabriciolfj.domain.integration.http.dto.StockResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/stock")
@RegisterRestClient
public interface StockHttp {

    @GET
    @Path("{code}")
    StockResponse getStock(@PathParam("code") final String code);
}
