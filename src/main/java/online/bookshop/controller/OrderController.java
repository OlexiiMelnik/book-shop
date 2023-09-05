package online.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import online.bookshop.dto.order.OrderRequestDto;
import online.bookshop.dto.order.OrderResponseDto;
import online.bookshop.dto.order.OrderUpdateStatusDto;
import online.bookshop.dto.orderitem.OrderItemResponseDto;
import online.bookshop.model.User;
import online.bookshop.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order management", description = "Endpoints for managing order")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Create a new order",
            description = "Create a new order")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderResponseDto create(Authentication authentication,
                                   @RequestBody @Valid OrderRequestDto requestDto) {
        User user = (User) authentication.getPrincipal();
        return orderService.create(user.getId(), requestDto);
    }

    @Operation(summary = "User order history",
            description = "Find all the orders by user")
    @GetMapping
    public List<OrderResponseDto> findAllByCurrentUser(
            Authentication authentication, Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return orderService.findAllByUserId(user.getId(), pageable);
    }

    @Operation(summary = "Update status of order",
            description = "Update status of order")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public OrderResponseDto updateStatus(@PathVariable Long id,
                                         @RequestBody OrderUpdateStatusDto orderUpdateStatusDto) {
        return orderService.updateStatus(id, orderUpdateStatusDto);
    }

    @Operation(summary = "Find all orderItems by order",
            description = "Find all orderItems by order")
    @GetMapping("/{id}/items")
    public Set<OrderItemResponseDto> findAllOrderItems(@PathVariable Long id) {
        return orderService.findAllOrderItems(id);
    }

    @Operation(summary = "Find orderItem by orderId and itemId",
            description = "Find orderItem by orderId and itemId")
    @GetMapping("/{orderId}/items/{itemId}")
    public OrderItemResponseDto findOrderItemById(
            @PathVariable Long orderId, @PathVariable Long itemId) {
        return orderService.findOrderItemById(orderId, itemId);
    }
}
