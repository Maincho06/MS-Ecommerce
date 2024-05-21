package com.mauricio.dev.service;


import com.mauricio.dev.event.Event;
import com.mauricio.dev.event.OrderPaidCreateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderPaidEvent {


    @Autowired
    private InventaryService inventaryService;

    @KafkaListener(
            topics = "${kafka.topic.name.order:order_paid}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "grupo1"
    )
    public void customer(Event<?> event) {

        if(event.getClass().isAssignableFrom(OrderPaidCreateEvent.class)) {
            OrderPaidCreateEvent orderPaidCreateEvent = (OrderPaidCreateEvent) event;
            this.inventaryService.registerTransaction(orderPaidCreateEvent.getData());
            log.info("Order received successfully .... " + orderPaidCreateEvent.toString());
        }
    }

}
