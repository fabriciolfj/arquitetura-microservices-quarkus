package com.github.fabriciolfj.api.product.controller;

import com.github.fabriciolfj.api.product.dto.ProductRequest;
import com.github.fabriciolfj.api.product.dto.ProductResponse;
import com.github.fabriciolfj.domain.service.ProductService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("products")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ProductController {

    @Inject
    ProductService productService;

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
        productService.create(request, request.getCategory());
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
