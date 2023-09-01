package online.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import online.bookshop.dto.cartitem.CartItemRequestDto;
import online.bookshop.dto.cartitem.CartItemUpdateDto;
import online.bookshop.dto.shoppingcart.ShoppingCartResponseDto;
import online.bookshop.model.User;
import online.bookshop.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping_cart management", description = "Endpoints for managing shoppingCart")
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Operation(summary = "Find a shoppingCart",
            description = "Find a shoppingCart by userId into DB")
    @GetMapping()
    public ShoppingCartResponseDto getShoppingCartDtoByUserId(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.getShoppingCartDtoByUserId(user.getId());
    }

    @Operation(summary = "Add book to the shopping cart",
            description = "Add book to the shopping cart")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ShoppingCartResponseDto addBookToShoppingCart(
            Authentication authentication,
            @RequestBody @Valid CartItemRequestDto cartItemRequestDto) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addCartItemByUserId(user.getId(), cartItemRequestDto);
    }

    @Operation(summary = "Update quantity of a book in the shopping cart",
            description = "Update quantity of a book in the shopping cart")
    @PutMapping("cart-items/{cartItemId}")
    public ShoppingCartResponseDto updateCartItem(
            Authentication authentication,
            @PathVariable Long cartItemId,
            @RequestBody @Valid CartItemUpdateDto updateDto) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.updateCartItem(user.getId(), cartItemId, updateDto);
    }

    @Operation(summary = "Remove a book from the shopping cart",
            description = "Remove a book from the shopping cart by id into DB")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("cart-items/{cartItemId}")
    public ShoppingCartResponseDto deleteCartItem(
            Authentication authentication, @PathVariable Long cartItemId) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.deleteCartItem(user.getId(), cartItemId);
    }
}
