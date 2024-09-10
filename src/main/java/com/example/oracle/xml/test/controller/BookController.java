package com.example.oracle.xml.test.controller;

import java.util.List;
import com.example.oracle.xml.test.dto.book.BookDto;
import com.example.oracle.xml.test.dto.book.BookSearchParameters;
import com.example.oracle.xml.test.dto.book.CreateBookRequestDto;
import com.example.oracle.xml.test.service.book.BookService;

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
@RequestMapping(value = "/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BookDto createBook(@RequestBody CreateBookRequestDto requestDto) {
        return bookService.create(requestDto);
    }


    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public BookDto updateBook(
            @PathVariable Long id,
            @RequestBody CreateBookRequestDto requestDto
    ) {
        return bookService.updateById(id, requestDto);
    }

    @GetMapping
    public List<BookDto> getAll(
            @PageableDefault(size = 20, sort = "id") Pageable pageable
    ) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }


    @GetMapping("/search")
    public List<BookDto> search(
            BookSearchParameters searchParameters,
            @PageableDefault(size = 20, sort = "id") Pageable pageable
    ) {
        return bookService.search(searchParameters, pageable);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
