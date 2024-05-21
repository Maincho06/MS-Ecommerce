package com.mauricio.dev.service.interfaces;

import com.mauricio.dev.model.Product;

import java.util.List;

public interface IProductService {



    List<Product> findAll();
    Product findById(String id);
    Product save(Product product);


}
