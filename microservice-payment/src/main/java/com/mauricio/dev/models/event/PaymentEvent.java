package com.mauricio.dev.models.event;

import com.mauricio.dev.models.enums.PaymentStatus;
import com.mauricio.dev.models.external.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaymentEvent {

    private String paymentId;
    private String paymentStatus;
    private List<Product> products;

}
