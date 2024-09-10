package com.example.oracle.xml.test.service.category;

import java.util.List;
import java.util.Set;
import com.example.oracle.xml.test.dto.category.CategoryResponseDto;
import com.example.oracle.xml.test.dto.category.CreateCategoryRequestDto;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto save(CreateCategoryRequestDto requestDto);

    CategoryResponseDto updateById(Long id, CreateCategoryRequestDto requestDto);

    void deleteById(Long id);

    void isAllCategoriesPresent(Set<Long> categoryIds);

    void isEntityExist(Long id);
}
