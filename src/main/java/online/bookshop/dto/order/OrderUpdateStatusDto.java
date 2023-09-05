package online.bookshop.dto.order;

import lombok.Data;
import online.bookshop.model.Order;

@Data
public class OrderUpdateStatusDto {
    private Order.Status status;
}
