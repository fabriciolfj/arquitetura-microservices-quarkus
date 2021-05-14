package com.github.fabriciolfj.api.category.controller;

import com.github.fabriciolfj.api.category.dto.CategoryRequest;
import com.github.fabriciolfj.api.category.dto.CategoryResponse;
import com.github.fabriciolfj.api.category.mapper.CategoryMapper;
import com.github.fabriciolfj.domain.entity.Category;
import com.github.fabriciolfj.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.of;

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
    public List<CategoryResponse> findAll() {
        return categoryService.findAll()
                .stream().map(categoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GET
    @Path("{description}")
    public CategoryResponse findDescription(@PathParam("description") final String description) {
        final var category = categoryService.findByDescription(description);
        return categoryMapper.toResponse(category);
    }

    @POST
    public Response create(@Valid final CategoryRequest request) {
        return of(categoryMapper.toEntity(request))
                .map(c -> {
                    categoryService.save(c);
                    return c;
                }).map(c -> {
                    log.info("Category created: {}", c);
                    return Response.status(201).build();
                })
                .get();
    }
}
