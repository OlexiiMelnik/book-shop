package online.bookshop.service;

import java.util.List;
import online.bookshop.dto.BookResponseDto;
import online.bookshop.dto.CreateBookRequestDto;

public interface BookService {
    BookResponseDto save(CreateBookRequestDto requestDto);

    List<BookResponseDto> findAll();

    BookResponseDto findById(Long id);
}
