package online.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import online.bookshop.dto.BookResponseDto;
import online.bookshop.dto.BookSearchParametersDto;
import online.bookshop.dto.CreateBookRequestDto;
import online.bookshop.service.BookService;
import org.springframework.data.domain.Pageable;
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

@Tag(name = "Book management", description = "Endpoints for managing books")
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @Operation(summary = "Create a new book and save to DB",
            description = "Create a new book and save to DB")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BookResponseDto create(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @Operation(summary = "Update the book",
            description = "Update the book by id into DB")
    @PutMapping("/{id}")
    public BookResponseDto update(@PathVariable Long id,
                                  @RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.update(id, requestDto);
    }

    @Operation(summary = "Find all books",
            description = "Find all the books in the DB and display them using pagination")
    @GetMapping
    public List<BookResponseDto> findAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @Operation(summary = "Find a book",
            description = "Find a book by id into DB")
    @GetMapping("/{id}")
    public BookResponseDto findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @Operation(summary = "Search book's by parameters",
            description = "Search book's by parameters")
    @GetMapping("/search")
    public List<BookResponseDto> search(BookSearchParametersDto params, Pageable pageable) {
        return bookService.search(params, pageable);
    }

    @Operation(summary = "Delete a book",
            description = "Delete a book by id into DB")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
