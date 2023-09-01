package online.bookshop.mapper;

import online.bookshop.config.MapperConfig;
import online.bookshop.dto.cartitem.CartItemRequestDto;
import online.bookshop.dto.cartitem.CartItemResponseDto;
import online.bookshop.dto.cartitem.CartItemUpdateDto;
import online.bookshop.model.Book;
import online.bookshop.model.CartItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    @Mapping(target = "book", ignore = true)
    CartItem toEntity(CartItemRequestDto cartItemRequestDto);

    @Mapping(target = "book", ignore = true)
    CartItem toEntity(CartItemUpdateDto cartItemUpdateDto);

    @Mappings({
            @Mapping(target = "bookId", source = "book.id"),
            @Mapping(target = "bookTitle", source = "book.title")
    })
    CartItemResponseDto toDto(CartItem cartItem);

    @AfterMapping
    default void setBook(@MappingTarget CartItem cartItem,
                         CartItemRequestDto cartItemRequestDto) {
        Book book = new Book();
        book.setId(cartItemRequestDto.getBookId());
        cartItem.setBook(book);
    }
}
