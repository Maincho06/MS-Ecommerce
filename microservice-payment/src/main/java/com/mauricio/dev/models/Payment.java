package com.mauricio.dev.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Entity
@Data
public class Payment {

    @Id
    private String paymentId;
    private String orderId;
    private double amount;
    private String paymentMethod;
    private String status;

    public Payment() {
        this.paymentId = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
