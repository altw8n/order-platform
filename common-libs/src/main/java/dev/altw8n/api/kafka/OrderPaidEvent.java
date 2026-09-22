package dev.altw8n.api.kafka;

import dev.altw8n.api.http.payment.PaymentMethod;

import java.math.BigDecimal;

public record OrderPaidEvent(
        Long orderId,
        Long paymentId,
        BigDecimal amount,
        PaymentMethod paymentMethod
) {
}
