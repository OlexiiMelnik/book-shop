package online.bookshop.repository;

import online.bookshop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteCartItemByShoppingCartId(Long shopCartId);
}
