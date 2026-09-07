package dev.altw8n.orderservice.api;

import dev.altw8n.orderservice.domain.OrderItemEntity;

import java.util.Set;

public record CreateOrderRequestDto(
    Set<OrderItemRequestDto> items,
    Long customerId,
    String address
) {}
