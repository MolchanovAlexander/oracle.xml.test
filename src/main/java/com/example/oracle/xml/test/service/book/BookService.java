package com.example.oracle.xml.test.service.book;

import java.util.List;
import com.example.oracle.xml.test.dto.book.BookDto;
import com.example.oracle.xml.test.dto.book.BookDtoWithoutCategories;
import com.example.oracle.xml.test.dto.book.BookSearchParameters;
import com.example.oracle.xml.test.dto.book.CreateBookRequestDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    List<BookDtoWithoutCategories> findAllByCategoryId(Long id, Pageable pageable);

    BookDto create(CreateBookRequestDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    void deleteById(Long id);

    BookDto updateById(Long id, CreateBookRequestDto requestDto);

    List<BookDto> search(BookSearchParameters params, Pageable pageable);

    void isExist(Long id);
}
