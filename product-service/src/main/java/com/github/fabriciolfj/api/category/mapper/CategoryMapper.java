package com.github.fabriciolfj.api.category.mapper;

import com.github.fabriciolfj.api.category.dto.CategoryRequest;
import com.github.fabriciolfj.domain.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface CategoryMapper {

    Category toEntity(final CategoryRequest request);
}
