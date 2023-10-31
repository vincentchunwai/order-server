package com.dev.orderservice.config;

import com.dev.orderservice.model.Instrument;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class InstrumentSerializer extends JsonSerializer<Instrument> {
    @Override
    public void serialize(Instrument instrument, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("symbol", instrument.getSymbol());
        jsonGenerator.writeStringField("symbolSfx", instrument.getSymbolSfx());
        jsonGenerator.writeStringField("securityID", instrument.getSecurityID());
        jsonGenerator.writeStringField("securityIDSource", instrument.getSecurityIDSource());
        jsonGenerator.writeStringField("product", instrument.getProduct());
        jsonGenerator.writeStringField("cfiCode", instrument.getCfiCode());
        jsonGenerator.writeStringField("securityType", instrument.getSecurityType());
        jsonGenerator.writeStringField("maturityMonthYear", instrument.getMaturityMonthYear());
        jsonGenerator.writeStringField("maturityDate", instrument.getMaturityDate());
        
        jsonGenerator.writeEndObject();
    }
}

