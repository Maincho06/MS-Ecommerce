package com.mauricio.dev.event;

import com.mauricio.dev.models.enums.EventType;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ToString
public abstract class Event<T> {

    private String id;
    /*private Date date;*/
    private EventType eventType;
    private T data;

}
