package com.github.fabriciolfj.domain.integration.mensageria;

import com.github.fabriciolfj.api.product.dto.StockRequest;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;

@ApplicationScoped
public class StockIntegration {

    @Inject
    @Channel("product")
    private Emitter<String> emitter;

    public void send(final StockRequest stockRequest) {
        final var json = JsonbBuilder.create();
        emitter.send(json.toJson(stockRequest));
    }
}
