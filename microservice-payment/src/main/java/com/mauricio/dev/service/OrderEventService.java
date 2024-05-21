package com.mauricio.dev.service;


import com.mauricio.dev.event.Event;
import com.mauricio.dev.event.OrderCreatedEvent;
import com.mauricio.dev.models.Payment;
import com.mauricio.dev.models.dtos.PaymentDTO;
import com.mauricio.dev.models.enums.PaymentMethod;
import com.mauricio.dev.models.enums.PaymentStatus;
import com.mauricio.dev.models.event.PaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderEventService {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @KafkaListener(
            topics = "${kafka.topic.name.order:order_created}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "grupo1"
    )
    public void customer(Event<?> event) {

        if(event.getClass().isAssignableFrom(OrderCreatedEvent.class)) {
            OrderCreatedEvent orderCreatedEvent = (OrderCreatedEvent) event;
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setOrderId(orderCreatedEvent.getData().getOrderId());
            paymentDTO.setPaymentMethod(PaymentMethod.CREDIT_CARD);
            paymentDTO.setAmount(orderCreatedEvent.getData().getTotalPrice());
            paymentDTO.setStatus(orderCreatedEvent.getData().getStatus().equalsIgnoreCase("pending")
                    ? PaymentStatus.APPROVED : PaymentStatus.REJECT);
            paymentDTO.setProductList(orderCreatedEvent.getData().getProducts());
            Payment payment = paymentService.save(paymentDTO);
            PaymentEvent paymentEvent =
                    new PaymentEvent(payment.getPaymentId(),payment.getStatus(),orderCreatedEvent.getData().getProducts());
            kafkaMessagePublisher.sendMessageToTopic(paymentEvent);
            log.info("Order created successfully .... " + payment.toString());
        }

    }


}
