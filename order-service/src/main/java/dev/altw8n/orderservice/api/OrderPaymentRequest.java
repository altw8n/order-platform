package dev.altw8n.orderservice.api;

import dev.altw8n.api.http.payment.PaymentMethod;

public record OrderPaymentRequest(
        PaymentMethod paymentMethod
) {}
