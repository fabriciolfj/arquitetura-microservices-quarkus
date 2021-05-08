package com.github.fabriciolfj.api.product.mapper;

import com.github.fabriciolfj.api.product.dto.ProductRequest;
import com.github.fabriciolfj.api.product.dto.StockRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface StockMapper {

    @Mapping(target = "stock", source = "request.stock")
    @Mapping(target = "code", source = "code")
    StockRequest toRequest(final ProductRequest request, final String code);
}
