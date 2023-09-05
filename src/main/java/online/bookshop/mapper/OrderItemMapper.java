package online.bookshop.mapper;

import online.bookshop.config.MapperConfig;
import online.bookshop.dto.orderitem.OrderItemResponseDto;
import online.bookshop.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(target = "bookId", source = "book.id")
    OrderItemResponseDto toDto(OrderItem orderItem);
}
