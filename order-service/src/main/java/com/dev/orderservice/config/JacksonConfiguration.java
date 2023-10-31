package com.dev.orderservice.config;

import com.dev.orderservice.model.Instrument;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {

    @Bean
    public Module instrumentSerializerModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Instrument.class, new InstrumentSerializer());
        module.addDeserializer(Instrument.class, new InstrumentDeserializer());
        return module;
    }

    @Bean
    public ObjectMapper customObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(instrumentSerializerModule());
        return objectMapper;
    }
}
