package com.mauricio.dev.config;

import com.mauricio.dev.event.Event;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    private final String bootstrapAddress = "localhost:29092";

    @Bean
    public ConsumerFactory<String, Event<?>> consumerFactory() {

        Map<String , Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                JsonSerializer.TYPE_MAPPINGS,
                "com.mauricio.dev:com.mauricio.dev.event.Event"
        );

        final JsonDeserializer<Event<?>> jsonDeserializer = new JsonDeserializer();
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                jsonDeserializer
        );

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,Event<?>> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String , Event<?>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());
        return factory;

    }

}
