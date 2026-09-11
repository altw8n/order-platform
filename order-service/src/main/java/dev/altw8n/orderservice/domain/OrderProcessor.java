package dev.altw8n.orderservice.domain;

import dev.altw8n.api.http.order.OrderStatus;
import dev.altw8n.api.http.order.CreateOrderRequestDto;
import dev.altw8n.api.http.payment.CreatePaymentRequestDto;
import dev.altw8n.api.http.payment.PaymentStatus;
import dev.altw8n.orderservice.api.OrderPaymentRequest;
import dev.altw8n.orderservice.domain.db.OrderEntity;
import dev.altw8n.orderservice.domain.db.OrderEntityMapper;
import dev.altw8n.orderservice.domain.db.OrderItemEntity;
import dev.altw8n.orderservice.domain.db.OrderJpaRepository;
import dev.altw8n.orderservice.external.PaymentHttpClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class OrderProcessor {
    private final OrderJpaRepository orderJpaRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final PaymentHttpClient paymentHttpClient;

    public OrderProcessor(
            OrderJpaRepository orderJpaRepository,
            OrderEntityMapper orderEntityMapper,
            PaymentHttpClient paymentHttpClient
            )
    {
        this.orderEntityMapper = orderEntityMapper;
        this.orderJpaRepository = orderJpaRepository;
        this.paymentHttpClient = paymentHttpClient;
    }

    public OrderEntity create(CreateOrderRequestDto request){
        var entity = orderEntityMapper.toEntity(request);
        calculatePricingForOrder(entity);
        entity.setOrderStatus(OrderStatus.PENDING_PAYMENT);
        return orderJpaRepository.save(entity);
    }

    public OrderEntity getOrderOrThrow(Long id){
        var orderEntityOptional = orderJpaRepository.findById(id);
        return orderEntityOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
    }

    private void calculatePricingForOrder(OrderEntity entity) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItemEntity item : entity.getItems()){
            var randomPrice = ThreadLocalRandom.current().nextDouble(100, 5000);
            item.setPriceAtPurchase(BigDecimal.valueOf(randomPrice));
            totalPrice = item.getPriceAtPurchase()
                    .multiply(BigDecimal.valueOf(item.getQuantity()))
                    .add(totalPrice);
        }
        entity.setTotalAmount(totalPrice);
    }

    public OrderEntity processPayment(Long id, OrderPaymentRequest request) {
        var entity = getOrderOrThrow(id);
        if (!entity.getOrderStatus().equals(OrderStatus.PENDING_PAYMENT)){
            throw new RuntimeException("order must be in status PENDING_PAYMENT");
        }
        CreatePaymentRequestDto paymentDto = new CreatePaymentRequestDto(
                entity.getId(),
                request.paymentMethod(),
                entity.getTotalAmount()
        );
        var response = paymentHttpClient.createPayment(paymentDto);
        if (response.paymentStatus().equals(PaymentStatus.PAYMENT_SUCCEEDED)){
            entity.setOrderStatus(OrderStatus.PAYMENT_FAILED);
        } else{
            entity.setOrderStatus(OrderStatus.PAID);
        }
        return orderJpaRepository.save(entity);
    }
}

