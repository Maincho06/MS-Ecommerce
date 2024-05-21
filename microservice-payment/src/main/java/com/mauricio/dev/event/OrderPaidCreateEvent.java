package com.mauricio.dev.event;

import com.mauricio.dev.models.event.PaymentEvent;
import lombok.Data;

@Data
public class OrderPaidCreateEvent extends Event<PaymentEvent> {

}
