package com.github.fabriciolfj.core.integration.mapper;

import com.github.fabriciolfj.core.integration.dto.StockMessage;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class StockMessageDeserializer extends ObjectMapperDeserializer<StockMessage> {

    public StockMessageDeserializer() {
        super(StockMessage.class);
    }
}
