package online.bookshop.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import online.bookshop.dto.BookResponseDto;
import online.bookshop.dto.CreateBookRequestDto;
import online.bookshop.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public BookResponseDto create(@RequestBody CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @GetMapping
    public List<BookResponseDto> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookResponseDto findById(@PathVariable Long id) {
        return bookService.findById(id);
    }
}
