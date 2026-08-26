package dev.altw8n.orderservice.domain;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class OrderProcessor {
    private final OrderJpaRepository orderJpaRepository;

    public OrderProcessor(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    public OrderEntity create(OrderEntity orderEntity){
        return orderJpaRepository.save(orderEntity);
    }

    public OrderEntity getOrderOrThrow(Long id){
        var orderEntityOptional = orderJpaRepository.findById(id);
        return orderEntityOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
    }
}
