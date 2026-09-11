package dev.altw8n.orderservice.api;

import dev.altw8n.api.http.order.OrderDto;
import dev.altw8n.orderservice.domain.*;
import dev.altw8n.orderservice.domain.db.OrderEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderProcessor orderProcessor;
    private final OrderEntityMapper orderEntityMapper;

    public OrderController(OrderProcessor orderProcessor, OrderEntityMapper orderEntityMapper) {
        this.orderProcessor = orderProcessor;
        this.orderEntityMapper = orderEntityMapper;
    }

    @PostMapping("/{id}/pay")
    public OrderDto payOrder(
            @PathVariable Long id,
            @RequestBody OrderPaymentRequest request
    ){
        log.info("paying order");
        var saved = orderProcessor.processPayment(id, request);
        return orderEntityMapper.toOrderDto(saved);
    }

    @GetMapping("/{id}")
    public OrderDto getOne(@PathVariable Long id){
        log.info("retrieving order");
        var found = orderProcessor.getOrderOrThrow(id);
        return orderEntityMapper.toOrderDto(found);
    }
}
