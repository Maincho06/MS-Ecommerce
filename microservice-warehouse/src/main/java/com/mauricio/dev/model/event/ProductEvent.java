package com.mauricio.dev.model.event;


import lombok.Data;

@Data
public class ProductEvent {
    private String productId;
    private Integer count;
}
