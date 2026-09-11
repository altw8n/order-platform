package dev.altw8n.paymentservice.domain;

import dev.altw8n.api.http.payment.CreatePaymentRequestDto;
import dev.altw8n.paymentservice.api.PaymentController;
import dev.altw8n.api.http.payment.PaymentMethod;
import dev.altw8n.api.http.payment.PaymentStatus;
import dev.altw8n.paymentservice.domain.db.PaymentEntityMapper;
import dev.altw8n.paymentservice.domain.db.PaymentEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentEntityRepository repository;
    private final PaymentEntityMapper mapper;

    public PaymentService(PaymentEntityRepository repository, PaymentEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public dev.altw8n.api.http.payment.CreatePaymentResponseDto makePayment(CreatePaymentRequestDto request){
        var found = repository.findByOrderId(request.orderId());
        if (found.isPresent()){
            log.info("payment is already exists");
            return mapper.toResponseDto(found.get());
        }

        var entity = mapper.toEntity(request);
        var status = request.paymentMethod().equals(PaymentMethod.QR)
                ? PaymentStatus.PAYMENT_FAILED
                : PaymentStatus.PAYMENT_SUCCEEDED;

        entity.setPaymentStatus(status);

        var saved = repository.save(entity);
        return mapper.toResponseDto(saved);
    }
}
