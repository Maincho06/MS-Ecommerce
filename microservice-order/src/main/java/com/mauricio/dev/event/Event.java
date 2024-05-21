package com.mauricio.dev.event;


import lombok.Data;
import lombok.ToString;

@ToString
@Data
public abstract class Event<T> {

    private String id;
    /*private Date date;*/
    private EventType eventType;
    private T data;

}
