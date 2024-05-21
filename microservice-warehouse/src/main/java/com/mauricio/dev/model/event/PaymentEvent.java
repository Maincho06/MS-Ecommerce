package com.mauricio.dev.model.event;


import com.mauricio.dev.event.Event;
import com.mauricio.dev.model.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class PaymentEvent  {

    private String paymentId;
    private String status;
    private List<ProductEvent> products;


    @Override
    public String toString() {
        return "PaymentEvent{" +
                "paymentId='" + paymentId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
