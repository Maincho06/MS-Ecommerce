package com.mauricio.dev.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Product {

    @Id
    private String productId;
    private String productName;
    private double unitPrice;
    private Integer stock;

    public Product() {
        this.productId = UUID.randomUUID().toString();
    }
}
