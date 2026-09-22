package dev.altw8n.orderservice.kafka;


import dev.altw8n.api.kafka.DeliveryAssignedEvent;
import dev.altw8n.orderservice.domain.OrderProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@Configuration
public class DeliveryAssignedKafkaConsumer {

    private final OrderProcessor orderProcessor;
    private static final Logger log = LoggerFactory.getLogger(DeliveryAssignedKafkaConsumer.class);

    public DeliveryAssignedKafkaConsumer(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    @KafkaListener(
            topics = "${delivery-assigned-topic}",
            containerFactory = "deliveryAssignedEventEventListenerFactory"
    )
    public void listen(DeliveryAssignedEvent event) {
        log.info("Received delivery assigned event: {}", event);
        orderProcessor.processDeliveryAssigned(event);
    }
}