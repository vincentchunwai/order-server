package com.dev.orderservice.config;

import com.dev.orderservice.model.Instrument;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class InstrumentDeserializer extends JsonDeserializer<Instrument> {

    @Override
    public Instrument deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String symbol = node.get("symbol").asText();
        String securityID = node.get("securityID").asText();
        String symbolSfx = node.get("symbolSfx").asText();
        String securityIDSource = node.get("securityIDSource").asText();
        String product = node.get("product").asText();
        String cfiCode = node.get("cfiCode").asText();
        String securityType = node.get("securityType").asText();
        String maturityMonthYear = node.get("maturityMonthYear").asText();
        String maturityDate = node.get("maturityDate").asText();

        return new Instrument
        (symbol, symbolSfx, product, securityID, securityIDSource, cfiCode, securityType, maturityMonthYear, maturityDate);
    }
}
