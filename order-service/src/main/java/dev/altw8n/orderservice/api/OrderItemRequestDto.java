package dev.altw8n.orderservice.api;

public record OrderItemRequestDto(
    Long itemId,
    Integer quantity,
    String name
) {}
