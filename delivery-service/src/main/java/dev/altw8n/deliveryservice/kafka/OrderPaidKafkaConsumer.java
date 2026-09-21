package dev.altw8n.deliveryservice.kafka;

import dev.altw8n.api.kafka.OrderPaidEvent;
import dev.altw8n.deliveryservice.domain.DeliveryEntity;
import dev.altw8n.deliveryservice.domain.DeliveryEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.ThreadLocalRandom;

@EnableKafka
@Configuration
public class OrderPaidKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderPaidKafkaConsumer.class);
    private final DeliveryEntityRepository repository;


    public OrderPaidKafkaConsumer(DeliveryEntityRepository repository){
        this.repository = repository;
    }

    @KafkaListener
    public void listen(OrderPaidEvent event) {
        log.info("received order paid event");
        var orderId = event.orderId();
        var found = repository.findByOrderId(orderId);
        if (found.isPresent()){
            log.info("found order delivery was already assigned");
            return;
        }
        assignDelivery(orderId);
    }

    private void assignDelivery(Long orderId){
        var entity = new DeliveryEntity();
        entity.setOrderId(orderId);
        entity.setCourierName("courier-" + ThreadLocalRandom.current().nextInt(1001));
        entity.setEtaMinutes(ThreadLocalRandom.current().nextInt(10, 45));
        repository.save(entity);
        log.info("saved order delivery was assigned");
    }
}
