package online.bookshop.mapper;

import online.bookshop.config.MapperConfig;
import online.bookshop.dto.book.BookResponseDto;
import online.bookshop.dto.book.CreateBookRequestDto;
import online.bookshop.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookResponseDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
