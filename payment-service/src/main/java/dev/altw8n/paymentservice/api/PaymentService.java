package dev.altw8n.paymentservice.api;

import dev.altw8n.paymentservice.domain.PaymentEntityRepository;
import dev.altw8n.paymentservice.domain.PaymentMethod;
import dev.altw8n.paymentservice.domain.PaymentStatus;
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

    public CreatePaymentResponseDto makePayment(CreatePaymentRequestDto request){
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
