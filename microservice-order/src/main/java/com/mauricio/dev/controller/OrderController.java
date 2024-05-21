package com.mauricio.dev.controller;


import com.mauricio.dev.models.Order;
import com.mauricio.dev.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private IOrderService orderService;
    @PostMapping()
    public ResponseEntity<Order> save(@RequestBody Order orderBody) {

        Order order = this.orderService.save(orderBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testGateway() {
        return ResponseEntity.ok("HOLA DESDE GATEWAY");
    }


}
