package com.mauricio.dev.models.external;


import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class Product {
    private String productId;
    private Integer count;
}
