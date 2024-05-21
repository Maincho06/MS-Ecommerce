package com.mauricio.dev.service.interfaces;

import com.mauricio.dev.models.Payment;
import com.mauricio.dev.models.dtos.PaymentDTO;

import java.util.List;

public interface IPaymentService {

    List<Payment> findAll();
    Payment findById(String id);
    Payment save(PaymentDTO payment);




}
