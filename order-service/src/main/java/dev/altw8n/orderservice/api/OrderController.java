package dev.altw8n.orderservice.api;

import dev.altw8n.orderservice.domain.*;
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

    @PostMapping
    public OrderDto create(@RequestBody CreateOrderRequestDto request){
        log.info("creating order");
        var saved = orderProcessor.create(request);
        return orderEntityMapper.toOrderDto(saved);
    }

    @GetMapping("/{id}")
    public OrderDto getOne(@PathVariable Long id){
        log.info("retrieving order");
        var found = orderProcessor.getOrderOrThrow(id);
        return orderEntityMapper.toOrderDto(found);
    }
}
