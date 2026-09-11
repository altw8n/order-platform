package dev.altw8n.paymentservice.domain.db;

import dev.altw8n.api.http.payment.PaymentMethod;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "order_id", nullable = false, unique = true)
    private Long orderId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private dev.altw8n.api.http.payment.PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    public PaymentEntity(){};

    public PaymentEntity(Long id, Long orderId, BigDecimal amount, dev.altw8n.api.http.payment.PaymentStatus paymentStatus, PaymentMethod paymentMethod) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public dev.altw8n.api.http.payment.PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPaymentStatus(dev.altw8n.api.http.payment.PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
