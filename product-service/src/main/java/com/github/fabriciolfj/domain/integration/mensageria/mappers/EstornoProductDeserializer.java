package com.github.fabriciolfj.domain.integration.mensageria.mappers;

import com.github.fabriciolfj.domain.integration.mensageria.dto.EstornoProduct;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class EstornoProductDeserializer extends ObjectMapperDeserializer<EstornoProduct> {

    public EstornoProductDeserializer() {
        super(EstornoProduct.class);
    }
}
