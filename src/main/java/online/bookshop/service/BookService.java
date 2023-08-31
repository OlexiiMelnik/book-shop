package online.bookshop.service;

import java.util.List;
import online.bookshop.dto.book.BookDto;
import online.bookshop.dto.book.BookDtoWithoutCategoryIds;
import online.bookshop.dto.book.BookSearchParametersDto;
import online.bookshop.dto.book.CreateBookRequestDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    void deleteById(Long id);

    BookDto update(Long id, CreateBookRequestDto requestDto);

    List<BookDto> search(BookSearchParametersDto params, Pageable pageable);

    List<BookDtoWithoutCategoryIds> findAllByCategoryId(Long categoryId, Pageable pageable);
}
