package online.bookshop.service.impl;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import online.bookshop.dto.order.OrderRequestDto;
import online.bookshop.dto.order.OrderResponseDto;
import online.bookshop.dto.order.OrderUpdateStatusDto;
import online.bookshop.dto.orderitem.OrderItemResponseDto;
import online.bookshop.exeption.EntityNotFoundException;
import online.bookshop.mapper.OrderItemMapper;
import online.bookshop.mapper.OrderMapper;
import online.bookshop.model.Order;
import online.bookshop.model.OrderItem;
import online.bookshop.model.ShoppingCart;
import online.bookshop.repository.CartItemRepository;
import online.bookshop.repository.OrderItemRepository;
import online.bookshop.repository.OrderRepository;
import online.bookshop.repository.ShoppingCartRepository;
import online.bookshop.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public OrderResponseDto create(Long id, OrderRequestDto requestDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find shopping cart by userID: " + id));
        Order order = new Order();
        order.setUser(shoppingCart.getUser());
        order.setStatus(Order.Status.NEW_ORDER);
        order.setOrderDate(LocalDateTime.now());
        order.setShippingAddress(requestDto.getShippingAddress());
        Set<OrderItem> orderItems =
                getOrderItemsFromShoppingCart(shoppingCart, order);
        order.setOrderItems(orderItems);
        BigDecimal total = calculateTotal(orderItems);
        order.setTotal(total);
        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);
        cartItemRepository.deleteCartItemByShoppingCartId(shoppingCart.getId());
        shoppingCart.setCartItems(new HashSet<>());
        shoppingCartRepository.save(shoppingCart);
        return orderMapper.toDto(order);
    }

    @Override
    @Transactional
    public List<OrderResponseDto> findAllByUserId(Long userId, Pageable pageable) {
        List<Order> allByUserId = orderRepository.findAllByUserId(userId);
        return allByUserId.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderResponseDto updateStatus(Long id,
                                         OrderUpdateStatusDto orderUpdateStatusDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find order by userID: " + id));
        order.setStatus(orderUpdateStatusDto.getStatus());
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public Set<OrderItemResponseDto> findAllOrderItems(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find order by userID: " + orderId));
        return order.getOrderItems().stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public OrderItemResponseDto findOrderItemById(Long orderId, Long itemId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find order by userID: " + orderId));
        return order.getOrderItems().stream()
                .filter(orderItem -> orderItem.getId().equals(itemId))
                .findFirst()
                .map(orderItemMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find order by userID: " + itemId));
    }

    private BigDecimal calculateTotal(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem -> orderItem.getBook().getPrice()
                        .multiply(new BigDecimal(orderItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Set<OrderItem> getOrderItemsFromShoppingCart(
            ShoppingCart shoppingCart, Order order) {
        return shoppingCart.getCartItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setBook(cartItem.getBook());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPrice(cartItem.getBook().getPrice());
                    return orderItem;
                }).collect(Collectors.toSet());
    }
}
