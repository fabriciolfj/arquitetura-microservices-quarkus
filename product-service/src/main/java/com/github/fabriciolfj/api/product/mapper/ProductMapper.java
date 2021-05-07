package com.github.fabriciolfj.api.product.mapper;

import com.github.fabriciolfj.api.product.dto.ProductRequest;
import com.github.fabriciolfj.api.product.dto.ProductResponse;
import com.github.fabriciolfj.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ProductMapper {

    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "code", source = "code")
    ProductResponse toResponse(final Product product);

    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product toEntity(final ProductRequest request);
}
