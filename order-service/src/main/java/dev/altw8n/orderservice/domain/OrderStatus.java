package dev.altw8n.orderservice.domain;

public enum OrderStatus {
    PENDING_PAYMENT,
    PAID,
    PAYMENT_FAILED,
    PENDING_DELIVERY,
    DELIVERED
}
