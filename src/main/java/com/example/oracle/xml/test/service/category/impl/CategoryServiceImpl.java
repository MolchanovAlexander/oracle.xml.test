package com.example.oracle.xml.test.service.category.impl;

import java.util.List;
import java.util.Set;
import com.example.oracle.xml.test.dto.category.CategoryResponseDto;
import com.example.oracle.xml.test.dto.category.CreateCategoryRequestDto;
import com.example.oracle.xml.test.exception.EntityNotFoundException;
import com.example.oracle.xml.test.mapper.CategoryMapper;
import com.example.oracle.xml.test.model.Category;
import com.example.oracle.xml.test.repository.category.CategoryRepository;
import com.example.oracle.xml.test.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDto save(CreateCategoryRequestDto requestDto) {
        Category category = categoryMapper.toModel(requestDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryResponseDto updateById(Long id, CreateCategoryRequestDto requestDto) {
        isEntityExist(id);
        Category category = categoryMapper.toModel(requestDto);
        category.setId(id);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryResponseDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find category by id: " + id)
        );
        return categoryMapper.toDto(category);
    }

    @Override
    public void deleteById(Long id) {
        isEntityExist(id);
        categoryRepository.deleteById(id);
    }

    public void isAllCategoriesPresent(Set<Long> categoryIds) {
        List<Category> categories = categoryRepository.findAllById(categoryIds);
        if (categories.size() != categoryIds.size()) {
            throw new EntityNotFoundException(
                    "Some of these categories are absent: " + categoryIds);
        }
    }

    public void isEntityExist(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "There is no category with id: " + id
            );
        }
    }
}
