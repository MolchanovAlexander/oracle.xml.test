package com.example.oracle.xml.test.service.book.impl;

import java.util.List;
import com.example.oracle.xml.test.dto.book.BookDto;
import com.example.oracle.xml.test.dto.book.BookDtoWithoutCategories;
import com.example.oracle.xml.test.dto.book.BookSearchParameters;
import com.example.oracle.xml.test.dto.book.CreateBookRequestDto;
import com.example.oracle.xml.test.exception.EntityNotFoundException;
import com.example.oracle.xml.test.mapper.BookMapper;
import com.example.oracle.xml.test.model.Book;
import com.example.oracle.xml.test.repository.book.BookRepository;
import com.example.oracle.xml.test.service.book.BookService;
import com.example.oracle.xml.test.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private static final String MESSAGE_BOOK_NOT_EXIST = "book";
    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final BookMapper bookMapper;


    @Override
    public List<BookDtoWithoutCategories> findAllByCategoryId(Long id, Pageable pageable) {
        categoryService.isEntityExist(id);
        return bookRepository.findAllByCategoryId(id, pageable).stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList();
    }

    @Override
    public BookDto create(CreateBookRequestDto requestDto) {
        categoryService.isAllCategoriesPresent(requestDto.getCategoryIds());
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto updateById(Long id, CreateBookRequestDto requestDto) {
        isExist(id);
        Book book = bookMapper.toModel(requestDto);
        book.setId(id);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id: " + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDto> search(BookSearchParameters params, Pageable pageable) {

        return bookRepository.findAll( pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        isExist(id);
        bookRepository.deleteById(id);
    }

    public void isExist(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "There is no " + MESSAGE_BOOK_NOT_EXIST + " with id: " + id
            );
        }
    }
}
