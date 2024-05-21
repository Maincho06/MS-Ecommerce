package com.mauricio.dev.service;

import com.mauricio.dev.event.Event;
import com.mauricio.dev.event.EventType;
import com.mauricio.dev.event.OrderCreatedEvent;
import com.mauricio.dev.models.Order;
import com.mauricio.dev.models.external.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Event<?>> producer;

    @Value("${kafka.topic.name.order}")
    private String topicName;

    public void sendMessageToTopic(Order order) {
        log.info("Topic " + this.topicName);
        List<Product> product = order.getProducts();
        OrderCreatedEvent eventOrder = new OrderCreatedEvent();
        eventOrder.setData(order);
        eventOrder.setId(UUID.randomUUID().toString());
        eventOrder.setEventType(EventType.CREATED);
        /*eventOrder.setDate(new Date());*/
        this.producer.send(this.topicName, eventOrder);
    }

}
