package com.mauricio.dev.service.interfaces;

import com.mauricio.dev.model.Product;
import com.mauricio.dev.model.event.PaymentEvent;
import com.mauricio.dev.model.event.ProductEvent;

import java.util.List;

public interface IIventaryService {

    Product registerTransaction(PaymentEvent payment);

    Boolean validateStock(List<Product> products,List<ProductEvent> productOrder);

}
