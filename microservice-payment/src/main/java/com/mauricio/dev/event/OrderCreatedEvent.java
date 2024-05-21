package com.mauricio.dev.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.mauricio.dev.models.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeName("OrderCreatedEvent")
public class OrderCreatedEvent extends Event<Order>{

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OrderCreatedEvent { ");
        stringBuilder.append("id=").append(getId()).append(", ");
        stringBuilder.append("eventType=").append(getEventType()).append(", ");
        stringBuilder.append("data=").append(getData()).append(" }");
        return stringBuilder.toString();
    }

}
