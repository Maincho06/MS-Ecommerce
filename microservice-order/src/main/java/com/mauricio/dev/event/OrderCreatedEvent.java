package com.mauricio.dev.event;

import com.mauricio.dev.models.Order;
import com.mauricio.dev.models.external.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderCreatedEvent  extends Event<Order> {


}
