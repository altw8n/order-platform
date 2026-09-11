package dev.altw8n.api.http.order;

public enum OrderStatus {
    PENDING_PAYMENT,
    PAID,
    PAYMENT_FAILED,
    PENDING_DELIVERY,
    DELIVERED
}
