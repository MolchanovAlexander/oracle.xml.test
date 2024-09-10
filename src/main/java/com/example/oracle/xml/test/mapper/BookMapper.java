package com.example.oracle.xml.test.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import com.example.oracle.xml.test.config.MapperConfig;
import com.example.oracle.xml.test.dto.book.BookDto;
import com.example.oracle.xml.test.dto.book.BookDtoWithoutCategories;
import com.example.oracle.xml.test.dto.book.CreateBookRequestDto;
import com.example.oracle.xml.test.model.Book;
import com.example.oracle.xml.test.model.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    @Mapping(target = "categoryIds", ignore = true)
    BookDto toDto(Book book);

    BookDtoWithoutCategories toDtoWithoutCategories(Book book);

    @Mapping(target = "categories", ignore = true)
    Book toModel(CreateBookRequestDto requestDto);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        Set<Long> categoryIds = book.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet());
        bookDto.setCategoryIds(categoryIds);
    }

    @AfterMapping
    default void setCategories(CreateBookRequestDto requestDto, @MappingTarget Book book) {
        Set<Category> categories = requestDto.getCategoryIds().stream()
                .map(Category::new)
                .collect(Collectors.toSet());
        book.setCategories(categories);
    }
}
