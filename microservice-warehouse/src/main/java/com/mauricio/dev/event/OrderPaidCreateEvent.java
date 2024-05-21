package com.mauricio.dev.event;


import com.mauricio.dev.model.event.PaymentEvent;
import lombok.Data;

@Data
public class OrderPaidCreateEvent extends Event<PaymentEvent> {


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OrderPaidCreateEvent { ");
        stringBuilder.append("id=").append(getId()).append(", ");
        stringBuilder.append("eventType=").append(getEventType()).append(", ");
        stringBuilder.append("data=").append(getData()).append(" }");
        return stringBuilder.toString();
    }
}
