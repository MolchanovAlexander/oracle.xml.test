package com.example.oracle.xml.test.controller;

import java.util.List;
import com.example.oracle.xml.test.dto.book.BookDtoWithoutCategories;
import com.example.oracle.xml.test.dto.category.CategoryResponseDto;
import com.example.oracle.xml.test.dto.category.CreateCategoryRequestDto;
import com.example.oracle.xml.test.service.book.BookService;
import com.example.oracle.xml.test.service.category.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CategoryResponseDto createCategory(
             @RequestBody CreateCategoryRequestDto requestDto
    ) {
        return categoryService.save(requestDto);
    }


    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public CategoryResponseDto updateCategory(
            @PathVariable Long id,
             @RequestBody CreateCategoryRequestDto requestDto
    ) {
        return categoryService.updateById(id, requestDto);
    }


    @GetMapping
    public List<CategoryResponseDto> getAll(
             @PageableDefault(size = 20, sort = "id") Pageable pageable
    ) {
        return categoryService.findAll(pageable);
    }


    @GetMapping("/{id}/books")
    public List<BookDtoWithoutCategories> findAllByCategoryId(
            @PathVariable Long id, Pageable pageable
    ) {
        return bookService.findAllByCategoryId(id, pageable);
    }


    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
