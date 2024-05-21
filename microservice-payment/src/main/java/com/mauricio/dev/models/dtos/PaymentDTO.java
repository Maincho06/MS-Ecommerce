package com.mauricio.dev.models.dtos;

import com.mauricio.dev.models.enums.PaymentMethod;
import com.mauricio.dev.models.enums.PaymentStatus;
import com.mauricio.dev.models.external.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaymentDTO {

    private String orderId;
    private double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private List<Product> productList;

    public PaymentDTO() {
        this.productList = new ArrayList<>();
    }
}
