package dev.altw8n.api.kafka;


public record DeliveryAssignedEvent(
        Long orderId,
        String courierName,
        Integer etaMinutes
) {
}
