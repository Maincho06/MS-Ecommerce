package com.mauricio.dev.service;

import com.mauricio.dev.model.Product;
import com.mauricio.dev.model.event.PaymentEvent;
import com.mauricio.dev.model.event.ProductEvent;
import com.mauricio.dev.repository.ProductRepository;
import com.mauricio.dev.service.interfaces.IIventaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InventaryService implements IIventaryService {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product registerTransaction(PaymentEvent payment) {

        // Obtenemos todos los productos que recibimos en el evento
        List<Product> productsToUpdate = new ArrayList<>();
        for (ProductEvent productData : payment.getProducts()) {
            Product product = this.productService.findById(productData.getProductId());
            if(product != null) productsToUpdate.add(product);
        }

        // Lanzamos si sucede algo
        if ( validateStock(productsToUpdate, payment.getProducts()) ) {
            log.info("ERROR UNO DE LOS PRODUCTOS NO TIENE STOCK SUFICIENTE");
            // ROLL BACK
            return null;
        }

        for (Product product : productsToUpdate) {
            ProductEvent productOrder = payment.getProducts().stream()
                    .filter(pro -> pro.getProductId().equals(product.getProductId()))
                    .findFirst()
                    .orElse(null);
            if(productOrder != null){
                int count = product.getStock() - productOrder.getCount();
                product.setStock(count);
            }
        }
            /*productsToUpdate =  productsToUpdate.stream().map(product -> {

            ProductEvent productOrder = payment.getProducts().stream()
                    .filter(pro -> pro.getProductId().equals(product.getProductId()))
                    .findFirst()
                    .orElse(null);
            if(product != null) {
                int count = product.getStock();
                count = count - productOrder.getCount();
                product.setStock(count);
            }
            return product;
        }).collect(Collectors.toList());*/


        this.productRepository.saveAll(productsToUpdate);
        return null;
    }

    @Override
    public Boolean validateStock(List<Product> products, List<ProductEvent> productOrder) {
        boolean result = Boolean.FALSE;
        // TODO: Completar la logica de la validacion de productos
        for (Product product: products) {
            ProductEvent temp = productOrder.stream().filter(productOrd -> productOrd.getProductId().equals(product.getProductId())).findFirst().orElse(null);
            if(product.getStock() < temp.getCount()) {
                result = Boolean.TRUE;
                break;
            }
        }
        return result;
    }
}
