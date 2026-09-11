package dev.altw8n.orderservice.domain.db;

import dev.altw8n.api.http.order.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "customerId")
    private Long customerId;

    @Column(name = "address")
    private String address;

    @Column(name = "total_amount", precision = 19, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "courier_name")
    private String courierName;

    @Column(name = "eta_minutes")
    private Integer etaMinutes;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private Set<OrderItemEntity> items = new LinkedHashSet<>();

    public Set<OrderItemEntity> getItems() {
        return items;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getCourierName() {
        return courierName;
    }

    public Integer getEtaMinutes() {
        return etaMinutes;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public void setEtaMinutes(Integer etaMinutes) {
        this.etaMinutes = etaMinutes;
    }

    public void setItems(Set<OrderItemEntity> items) {
        this.items = items;
    }
}
