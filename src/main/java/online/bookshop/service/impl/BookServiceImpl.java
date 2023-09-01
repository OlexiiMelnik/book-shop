package online.bookshop.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import online.bookshop.dto.book.BookDto;
import online.bookshop.dto.book.BookDtoWithoutCategoryIds;
import online.bookshop.dto.book.BookSearchParametersDto;
import online.bookshop.dto.book.CreateBookRequestDto;
import online.bookshop.exeption.EntityNotFoundException;
import online.bookshop.mapper.BookMapper;
import online.bookshop.model.Book;
import online.bookshop.model.Category;
import online.bookshop.repository.BookRepository;
import online.bookshop.repository.CategoryRepository;
import online.bookshop.service.BookService;
import online.bookshop.specification.bookspec.BookSpecificationBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Set<Category> categories =
                categoryRepository.findByIdIn(requestDto.getCategoryIds());
        Book book = bookMapper.toEntity(requestDto);
        book.setCategories(categories);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find employee by id: " + id));
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto requestDto) {
        Set<Category> byIdIn =
                categoryRepository.findByIdIn(requestDto.getCategoryIds());
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find employee by id: " + id));
        book.setTitle(requestDto.getTitle());
        book.setAuthor(requestDto.getAuthor());
        book.setIsbn(requestDto.getIsbn());
        book.setDescription(requestDto.getDescription());
        book.setCoverImage(requestDto.getCoverImage());
        book.setCategories(byIdIn);
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDto> search(
            BookSearchParametersDto params, Pageable pageable) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(params);
        return bookRepository.findAll(bookSpecification, pageable).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDtoWithoutCategoryIds> findAllByCategoryId(Long categoryId, Pageable pageable) {
        return bookRepository.findAllByCategoryId(categoryId, pageable).stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList();
    }
}
