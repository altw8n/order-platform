package dev.altw8n.orderservice.api;

import dev.altw8n.orderservice.domain.OrderItemEntity;
import dev.altw8n.orderservice.domain.OrderStatus;

import java.math.BigDecimal;
import java.util.Set;

public record OrderDto(
        Long id,
        Long customerId,
        String addres,
        BigDecimal totalAmount,
        String courierName,
        Integer etaMinutes,
        OrderStatus orderStatus,
        Set<OrderItemEntity> orderItemEntities
) {
}
