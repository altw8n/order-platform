package dev.altw8n.api.http.payment;

import java.math.BigDecimal;

public record CreatePaymentRequestDto(
        Long orderId,
        PaymentMethod paymentMethod,
        BigDecimal amount
) {
}
