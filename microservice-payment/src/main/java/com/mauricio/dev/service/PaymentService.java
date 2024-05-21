package com.mauricio.dev.service;


import com.mauricio.dev.models.Payment;
import com.mauricio.dev.models.dtos.PaymentDTO;
import com.mauricio.dev.repository.PaymentRepository;
import com.mauricio.dev.service.interfaces.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public List<Payment> findAll() {
        return null;
    }

    @Override
    public Payment findById(String id) {
        return null;
    }

    @Override
    public Payment save(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod().name());
        payment.setStatus(paymentDTO.getStatus().name());
        return this.paymentRepository.save(payment);
    }
}
