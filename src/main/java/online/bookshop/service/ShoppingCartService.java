package online.bookshop.service;

import online.bookshop.dto.cartitem.CartItemRequestDto;
import online.bookshop.dto.cartitem.CartItemUpdateDto;
import online.bookshop.dto.shoppingcart.ShoppingCartResponseDto;

public interface ShoppingCartService {
    ShoppingCartResponseDto getShoppingCartDtoByUserId(Long id);

    ShoppingCartResponseDto addCartItemByUserId(Long id, CartItemRequestDto cartItemRequestDto);

    ShoppingCartResponseDto updateCartItem(Long id, Long cartItemId,
                                           CartItemUpdateDto cartItemUpdateDto);

    ShoppingCartResponseDto deleteCartItem(Long id, Long cartItemId);

}
