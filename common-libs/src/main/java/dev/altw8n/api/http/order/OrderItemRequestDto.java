package dev.altw8n.api.http.order;

public record OrderItemRequestDto(
    Long itemId,
    Integer quantity,
    String name
) {}
