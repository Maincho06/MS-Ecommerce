package com.mauricio.dev.models;


import com.mauricio.dev.models.external.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;

    private double totalPrice;
    private String status;

    @Transient
    private List<Product> products;
    //private Date createdAt;

    public Order() {
        this.orderId = UUID.randomUUID().toString();
    }
}
