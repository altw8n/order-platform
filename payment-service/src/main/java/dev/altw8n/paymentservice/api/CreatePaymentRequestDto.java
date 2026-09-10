package dev.altw8n.paymentservice.api;

import dev.altw8n.paymentservice.domain.PaymentMethod;

import java.math.BigDecimal;

public record CreatePaymentRequestDto(
        Long orderId,
        PaymentMethod paymentMethod,
        BigDecimal amount
) {
}
