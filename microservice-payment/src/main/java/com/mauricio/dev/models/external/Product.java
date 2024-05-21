package com.mauricio.dev.models.external;


import lombok.Data;

@Data
public class Product {
    private String productId;
    private Integer count;

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", count=" + count +
                '}';
    }
}
