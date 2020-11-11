package com.github.fabriciolfj.domain.service;

import com.github.fabriciolfj.api.dto.ProductRequest;
import com.github.fabriciolfj.api.dto.ProductResponse;
import com.github.fabriciolfj.api.mapper.ProductMapper;
import com.github.fabriciolfj.domain.entity.Product;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Optional.of;

@RequiredArgsConstructor
@ApplicationScoped
public class ProductService {

    private final ProductMapper mapper;

    public List<ProductResponse> findAll() {
        final List<Product> products = Product.listAll();

        if (products.isEmpty()) {
            return Collections.emptyList();
        }

        return products.stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    public ProductResponse findByCode(final String code) {
        final Product product = (Product) Product.find("code", code);
        return mapper.toResponse(product);
    }

    @Transactional
    public void create(final ProductRequest request) {
        of(mapper.toEntity(request))
                .map(p -> {
                    p.code = UUID.randomUUID().toString();
                    Product.persist(p);
                    return p;
                })
                .orElseThrow(() -> new RuntimeException("Fail to save product: " + request));
    }

    @Transactional
    public void update(final ProductRequest request, final String code) {
        final Product product = (Product) Product.find("code", code);
        of(product)
                .map(p -> {
                    p.description = request.getDescription();
                    p.price = request.getPrice();
                    Product.persist(p);
                    return p;
                })
                .orElseThrow(() -> new RuntimeException("Fail to update product: " + code));

    }

    @Transactional
    public void delete(final String code) {
        final Product product = (Product) Product.find("code", code);
        Product.deleteById(product.id);
    }
}
