package com.github.fabriciolfj.api.category.controller;

import com.github.fabriciolfj.api.category.dto.CategoryRequest;
import com.github.fabriciolfj.api.category.mapper.CategoryMapper;
import com.github.fabriciolfj.domain.entity.Category;
import com.github.fabriciolfj.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Slf4j
@Path("categories")
@ApplicationScoped
@RequiredArgsConstructor
@Produces("application/json")
@Consumes("application/json")
public class CategoryController {

    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    @GET
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GET
    @Path("description")
    public Category findDescription(@PathParam("description") final String description) {
        return categoryService.findByDescription(description);
    }

    @POST
    public Response create(final CategoryRequest request) {
        final var category = categoryMapper.toEntity(request);
        log.info("Category created: {}", category);
        categoryService.save(category);
        return Response.status(201).build();
    }
}
