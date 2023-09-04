package online.bookshop.service;

import java.util.List;
import java.util.Set;
import online.bookshop.dto.order.OrderRequestDto;
import online.bookshop.dto.order.OrderResponseDto;
import online.bookshop.dto.order.OrderUpdateStatusDto;
import online.bookshop.dto.orderitem.OrderItemResponseDto;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDto create(Long id, OrderRequestDto requestDto);

    List<OrderResponseDto> findAllByUserId(Long userId, Pageable pageable);

    OrderResponseDto updateStatus(Long id, OrderUpdateStatusDto orderUpdateStatusDto);

    Set<OrderItemResponseDto> findAllOrderItems(Long orderId);

    OrderItemResponseDto findOrderItemById(Long orderId, Long itemId);
}
