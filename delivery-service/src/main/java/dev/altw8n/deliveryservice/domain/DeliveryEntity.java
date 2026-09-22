package dev.altw8n.deliveryservice.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "deliveries")
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "order_id", nullable = false, unique = true)
    private Long orderId;

    @Column(name = "courier_name", nullable = false)
    private String courierName;

    @Column(name = "eta_minutes", nullable = false)
    private Integer etaMinutes;

    public DeliveryEntity(Long id, Long orderId, String courierName, Integer etaMinutes) {
        this.id = id;
        this.orderId = orderId;
        this.courierName = courierName;
        this.etaMinutes = etaMinutes;
    }

    public DeliveryEntity(){}

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCourierName() {
        return courierName;
    }

    public Integer getEtaMinutes() {
        return etaMinutes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public void setEtaMinutes(Integer etaMinutes) {
        this.etaMinutes = etaMinutes;
    }
}