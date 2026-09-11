package dev.altw8n.api.http.order;


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
        Set<OrderItemDto> orderItemEntities
) {
}
