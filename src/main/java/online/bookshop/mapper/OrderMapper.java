package online.bookshop.mapper;

import online.bookshop.config.MapperConfig;
import online.bookshop.dto.order.OrderResponseDto;
import online.bookshop.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    @Mapping(target = "userId", source = "user.id")
    OrderResponseDto toDto(Order order);

    Order toEntity(OrderResponseDto orderResponseDto);
}
