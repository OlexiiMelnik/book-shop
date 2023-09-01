package online.bookshop.service.impl;

import lombok.RequiredArgsConstructor;
import online.bookshop.dto.cartitem.CartItemRequestDto;
import online.bookshop.dto.cartitem.CartItemUpdateDto;
import online.bookshop.dto.shoppingcart.ShoppingCartResponseDto;
import online.bookshop.exeption.EntityNotFoundException;
import online.bookshop.mapper.CartItemMapper;
import online.bookshop.mapper.ShoppingCartMapper;
import online.bookshop.model.CartItem;
import online.bookshop.model.ShoppingCart;
import online.bookshop.model.User;
import online.bookshop.repository.CartItemRepository;
import online.bookshop.repository.ShoppingCartRepository;
import online.bookshop.repository.UserRepository;
import online.bookshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;

    @Override
    public ShoppingCartResponseDto getShoppingCartDtoByUserId(Long id) {
        ShoppingCart shoppingCartById = getShoppingCartById(id);
        return shoppingCartMapper.toDto(shoppingCartById);
    }

    @Override
    public ShoppingCartResponseDto addCartItemByUserId(Long id,
                                                       CartItemRequestDto cartItemRequestDto) {
        CartItem cartItem =
                cartItemMapper.toEntity(cartItemRequestDto);
        ShoppingCart shoppingCartById = getShoppingCartById(id);
        cartItem.setShoppingCart(shoppingCartById);
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(shoppingCartById);
    }

    @Override
    public ShoppingCartResponseDto updateCartItem(
            Long id, Long cartItemId, CartItemUpdateDto cartItemUpdateDto) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find cartItemId by id: " + cartItemId));
        cartItem.setQuantity(cartItemUpdateDto.getQuantity());
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(getShoppingCartById(id));
    }

    @Override
    public ShoppingCartResponseDto deleteCartItem(Long id, Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
        return shoppingCartMapper.toDto(getShoppingCartById(id));
    }

    private ShoppingCart getShoppingCartById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                "Can't find user by id: " + id));
        return shoppingCartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                "Can't find shopping cart by userID: " + user.getId()));
    }
}
