package com.github.fabriciolfj.api.product.mapper;

import com.github.fabriciolfj.api.product.dto.ProductRequest;
import com.github.fabriciolfj.api.product.dto.ProductResponse;
import com.github.fabriciolfj.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface ProductMapper {

    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "stock", ignore = true)
    ProductResponse toResponse(final Product product);

    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product toEntity(final ProductRequest request);

    @Mapping(target = "category", ignore = true)
    void update(final ProductRequest request, @MappingTarget final Product product);
}
