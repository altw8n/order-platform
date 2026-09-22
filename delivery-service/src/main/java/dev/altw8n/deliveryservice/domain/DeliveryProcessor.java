package dev.altw8n.deliveryservice.domain;


import dev.altw8n.api.kafka.DeliveryAssignedEvent;
import dev.altw8n.api.kafka.OrderPaidEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

import static org.apache.kafka.common.requests.DeleteAclsResponse.log;

@Slf4j
@Service
public class DeliveryProcessor {

    private final DeliveryEntityRepository repository;
    private final KafkaTemplate<Long, DeliveryAssignedEvent> kafkaTemplate;

    @Value("${delivery-assigned-topic}")
    private String deliveryAssignedTopic;

    public DeliveryProcessor(
            DeliveryEntityRepository repository,
            KafkaTemplate<Long, DeliveryAssignedEvent> kafkaTemplate
    ) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void processOrderPaid(OrderPaidEvent event) {
        var orderId = event.orderId();
        var found = repository.findByOrderId(orderId);
        if (found.isPresent()) {
            log.info("found order delivery was already assigned: delivery={}", found.get());
            return;
        }

        var assignedDelivery = assignDelivery(orderId);
        sendDeliveryAssignedEvent(assignedDelivery);
    }

    private DeliveryEntity assignDelivery(Long orderId) {
        var entity = new DeliveryEntity();
        entity.setOrderId(orderId);
        entity.setCourierName("courier-" + ThreadLocalRandom.current().nextInt(100));
        entity.setEtaMinutes(ThreadLocalRandom.current().nextInt(10, 45));
        log.info("saved order delivery was assigned: delivery={}", entity);

        return repository.save(entity);
    }

    private void sendDeliveryAssignedEvent(DeliveryEntity assignedDelivery) {
        kafkaTemplate.send(
                deliveryAssignedTopic,
                assignedDelivery.getOrderId(),
                new DeliveryAssignedEvent(
                        assignedDelivery.getOrderId(),
                        assignedDelivery.getCourierName(),
                        assignedDelivery.getEtaMinutes()
                )
        ).thenAccept(result -> {
            log.info("delivery assigned event sent: deliveryId={}", assignedDelivery.getId());
        });
    }
}