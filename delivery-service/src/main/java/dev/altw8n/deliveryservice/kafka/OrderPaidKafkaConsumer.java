package dev.altw8n.deliveryservice.kafka;

import dev.altw8n.api.kafka.OrderPaidEvent;
import dev.altw8n.deliveryservice.domain.DeliveryProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import static org.apache.kafka.common.requests.DeleteAclsResponse.log;

@Slf4j
@EnableKafka
@Configuration
public class OrderPaidKafkaConsumer {

    private final DeliveryProcessor deliveryProcessor;

    public OrderPaidKafkaConsumer(DeliveryProcessor deliveryProcessor) {
        this.deliveryProcessor = deliveryProcessor;
    }

    @KafkaListener(
            topics = "${order-paid-topic:orders.events}",
            groupId = "delivery-service-group"
    )
    public void listen(OrderPaidEvent event) {
        log.info("Received delivery assigned event: {}", event);
        deliveryProcessor.processOrderPaid(event);
    }

}