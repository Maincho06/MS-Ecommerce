package com.mauricio.dev.models;


import com.mauricio.dev.models.external.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {

    private String orderId;
    //private String customerId;
    private List<Product> products;
    private double totalPrice;
    private String status;

    public Order() {
        List<Product> products = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                '}';
    }
}
