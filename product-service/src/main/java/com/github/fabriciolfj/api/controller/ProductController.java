package com.github.fabriciolfj.api.controller;

import com.github.fabriciolfj.api.dto.ProductRequest;
import com.github.fabriciolfj.api.dto.ProductResponse;
import com.github.fabriciolfj.domain.service.ProductService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("products")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ProductController {

    @Inject
    private ProductService productService;

    @GET
    public List<ProductResponse> findAll() {
        return productService.findAll();
    }

    @GET
    @Path("{code}")
    public ProductResponse findByCode(@PathParam("code") final String code) {
        return productService.findByCode(code);
    }

    @POST
    public Response create(final ProductRequest request) {
        productService.create(request);
        return Response.status(201).build();
    }

    @PUT
    @Path("{code}")
    public Response update(final ProductRequest request, @PathParam("code") final String code) {
        productService.update(request, code);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{code}")
    public Response delete(@PathParam("code") final String code) {
        productService.delete(code);
        return Response.noContent().build();
    }
}
