package dev.altw8n.api.http.order;

import java.util.Set;

public record CreateOrderRequestDto(
    Set<dev.altw8n.api.http.order.OrderItemRequestDto> items,
    Long customerId,
    String address
) {}
