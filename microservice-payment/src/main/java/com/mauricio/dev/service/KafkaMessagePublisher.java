package com.mauricio.dev.service;

import com.mauricio.dev.event.Event;
import com.mauricio.dev.event.OrderCreatedEvent;
import com.mauricio.dev.event.OrderPaidCreateEvent;
import com.mauricio.dev.models.Order;
import com.mauricio.dev.models.Payment;
import com.mauricio.dev.models.enums.EventType;
import com.mauricio.dev.models.event.PaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Event<?>> producer;

    @Value("${kafka.topic.name.order_paid:order_paid}")
    private String topicName;

    public void sendMessageToTopic(PaymentEvent payment) {
        log.info("Topic " + this.topicName);
        OrderPaidCreateEvent eventPayment = new OrderPaidCreateEvent();
        eventPayment.setData(payment);
        eventPayment.setId(UUID.randomUUID().toString());
        eventPayment.setEventType(EventType.CREATED);
        this.producer.send(this.topicName, eventPayment);
    }

}
