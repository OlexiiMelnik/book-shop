package online.bookshop.service;

import java.util.List;
import online.bookshop.dto.book.BookResponseDto;
import online.bookshop.dto.book.BookSearchParametersDto;
import online.bookshop.dto.book.CreateBookRequestDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDto save(CreateBookRequestDto requestDto);

    List<BookResponseDto> findAll(Pageable pageable);

    BookResponseDto findById(Long id);

    void deleteById(Long id);

    BookResponseDto update(Long id, CreateBookRequestDto requestDto);

    List<BookResponseDto> search(BookSearchParametersDto params, Pageable pageable);
}
