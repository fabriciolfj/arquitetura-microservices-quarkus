package com.github.fabriciolfj.domain.service;

import com.github.fabriciolfj.api.product.dto.ProductRequest;
import com.github.fabriciolfj.api.product.dto.ProductResponse;
import com.github.fabriciolfj.api.product.mapper.ProductMapper;
import com.github.fabriciolfj.domain.entity.Product;
import com.github.fabriciolfj.domain.exceptions.ProductException;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Optional.of;

@RequiredArgsConstructor
@ApplicationScoped
public class ProductService {

    private final ProductMapper mapper;
    private final CategoryService categoryService;
    private final StockService stockService;

    @Transactional(Transactional.TxType.NEVER)
    public List<ProductResponse> findAll() {
        return Product.listAll().stream()
                .map(p -> (Product) p)
                .map(mapper::toResponse).collect(Collectors.toList());
    }

    @Transactional(Transactional.TxType.NEVER)
    public ProductResponse findByCode(final String code) {
        return Product.find("code", code)
                .firstResultOptional()
                .map(p -> (Product) p)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ProductException("product not found, code: " + code));
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void create(final ProductRequest request, final String decription) {
        of(mapper.toEntity(request))
                .map(p -> {
                    p.category = categoryService.findByDescription(decription);
                    p.code = UUID.randomUUID().toString();
                    Product.persist(p);
                    return p;
                })
                .map(p -> {
                    stockService.publisher(request, p.code);
                    return p;
                })
                .orElseThrow(() -> new RuntimeException("Fail to save product: " + request));
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void update(final ProductRequest request, final String code) {
        Product.find("code", code)
                .firstResultOptional()
                .map(p -> (Product) p)
                .map(p -> {
                    p.description = request.getDescription();
                    p.category = categoryService.findByDescription(request.getCategory());
                    p.price = request.getPrice();
                    Product.persist(p);
                    return p;
                })
                .orElseThrow(() -> new RuntimeException("Fail to update product: " + code));

    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(final String code) {
        Product.delete("code", code);
    }
}
