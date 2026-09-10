package dev.altw8n.paymentservice.api;

import dev.altw8n.paymentservice.domain.PaymentEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentEntityRepository repository;
    private final PaymentEntityMapper mapper;
    private final PaymentService paymentService;

    public PaymentController(PaymentEntityRepository paymentEntityRepository, PaymentEntityMapper mapper, PaymentService paymentService) {
        this.repository = paymentEntityRepository;
        this.mapper = mapper;
        this.paymentService = paymentService;
    }

    @PostMapping
    public CreatePaymentResponseDto createPayment(
            @RequestBody CreatePaymentRequestDto request
    ){
        log.info("received request");
        return paymentService.makePayment(request);
    }


}
