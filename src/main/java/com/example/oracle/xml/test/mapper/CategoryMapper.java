package com.example.oracle.xml.test.mapper;

import com.example.oracle.xml.test.config.MapperConfig;
import com.example.oracle.xml.test.dto.category.CategoryResponseDto;
import com.example.oracle.xml.test.dto.category.CreateCategoryRequestDto;
import com.example.oracle.xml.test.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryResponseDto toDto(Category category);

    Category toModel(CreateCategoryRequestDto requestDto);
}
