package dev.altw8n.paymentservice.api;

import dev.altw8n.paymentservice.domain.PaymentMethod;
import dev.altw8n.paymentservice.domain.PaymentStatus;

import java.math.BigDecimal;

public record CreatePaymentResponseDto(
        Long paymentId,
        PaymentStatus paymentStatus,
        Long orderId,
        PaymentMethod paymentMethod,
        BigDecimal amount
) {
}
