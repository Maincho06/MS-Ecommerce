package com.mauricio.dev.service;

import com.mauricio.dev.models.Order;
import com.mauricio.dev.repository.OrderRepository;
import com.mauricio.dev.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {


    @Autowired
    private OrderRepository orderRepository;
    private KafkaMessagePublisher kafkaMessagePublisher;

    public OrderService(KafkaMessagePublisher kafkaMessagePublisher) {
        super();
        this.kafkaMessagePublisher = kafkaMessagePublisher;
    }

    @Override
    public Order save(Order orderDTO) {
        Order order = new Order();
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setStatus(orderDTO.getStatus());
        order.setProducts(orderDTO.getProducts());
        Order orderNew =  this.orderRepository.save(order);
        orderNew.setProducts(orderDTO.getProducts());
        kafkaMessagePublisher.sendMessageToTopic(orderNew);
        return orderNew;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order findById(String id) {
        return null;
    }
}
