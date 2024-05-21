package com.mauricio.dev.service.interfaces;

import com.mauricio.dev.models.Order;

import java.util.List;

public interface IOrderService {

    Order save(Order order);

    List<Order> findAll();

    Order findById(String id);

}
