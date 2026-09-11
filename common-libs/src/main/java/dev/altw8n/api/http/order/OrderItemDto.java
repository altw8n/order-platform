package dev.altw8n.api.http.order;

import java.math.BigDecimal;

public record OrderItemDto(
        Long id,
        Long itemId,
        Integer quantity,
        BigDecimal priceAtPurchase
) {
}
