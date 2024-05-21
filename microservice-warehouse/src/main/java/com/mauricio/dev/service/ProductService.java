package com.mauricio.dev.service;

import com.mauricio.dev.model.Product;
import com.mauricio.dev.repository.ProductRepository;
import com.mauricio.dev.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product findById(String id) {
        Optional<Product> product = this.productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }
}
