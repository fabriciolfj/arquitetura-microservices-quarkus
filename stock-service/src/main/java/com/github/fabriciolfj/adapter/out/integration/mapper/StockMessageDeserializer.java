package com.github.fabriciolfj.adapter.out.integration.mapper;

import com.github.fabriciolfj.adapter.out.integration.dto.StockMessage;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class StockMessageDeserializer extends ObjectMapperDeserializer<StockMessage> {

    public StockMessageDeserializer() {
        super(StockMessage.class);
    }
}
