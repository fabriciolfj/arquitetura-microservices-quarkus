package com.github.fabriciolfj.domain.service;

import com.github.fabriciolfj.api.product.dto.ProductRequest;
import com.github.fabriciolfj.api.product.dto.ProductResponse;
import com.github.fabriciolfj.api.product.mapper.ProductMapper;
import com.github.fabriciolfj.domain.entity.Product;
import com.github.fabriciolfj.domain.exceptions.ProductException;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Optional.of;
import static java.util.concurrent.CompletableFuture.runAsync;

@Slf4j
@ApplicationScoped
public class ProductService {

    @Inject ProductMapper mapper;
    @Inject CategoryService categoryService;
    @Inject StockService stockService;

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<ProductResponse> findAll() {
        return Product.listAll().stream()
                .map(p -> (Product) p)
                .map(mapper::toResponse).collect(Collectors.toList());
    }

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ProductResponse findByCode(final String code) {
        return Product.find("code", code)
                .firstResultOptional()
                .map(p -> (Product) p)
                .map(mapper::toResponse)
                .map(p -> {
                    p.setStock( stockService.getStock(p.getCode()));
                    return p;
                })
                .orElseThrow(() -> new ProductException("product not found, code: " + code));
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void create(final ProductRequest request, final String decription) {
        of(mapper.toEntity(request))
                .map(p -> {
                    p.category = categoryService.findByDescription(decription);
                    p.code = UUID.randomUUID().toString();
                    save(p);
                    return p;
                })
                .map(p -> {
                    runAsync(() -> stockService.publisher(request, p.code))
                            .whenComplete((r, e) -> {
                                if (!Objects.isNull(e)) {
                                    throw new ProductException("Fail set stock: " + e.getMessage());
                                }
                            });
                    return p;
                })
                .orElseThrow(() -> new ProductException("Fail to save product: " + request));
    }

    private void save(Product p) {
        try {
            Product.persist(p);
        } catch (Exception e) {
            throw new ProductException("fail commit, cause: " + e.getCause());
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void update(final ProductRequest request, final String code) {
        Product.find("code", code)
                .firstResultOptional()
                .map(p -> (Product) p)
                .map(p -> {
                    mapper.update(request, p);
                    p.category = categoryService.findByDescription(request.getCategory());
                    save(p);
                    return p;
                })
                .orElseThrow(() -> new ProductException("Fail to update product: " + code));

    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(final String code) {
        Product.delete("code", code);
    }
}
