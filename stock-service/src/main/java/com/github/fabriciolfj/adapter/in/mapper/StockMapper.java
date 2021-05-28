package com.github.fabriciolfj.adapter.in.mapper;

import com.github.fabriciolfj.adapter.in.dto.StockResponse;
import com.github.fabriciolfj.core.entity.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface StockMapper {

    StockResponse toResponse(final Stock stock);
}
