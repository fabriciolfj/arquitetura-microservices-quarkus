package com.github.fabriciolfj.domain.integration.mensageria;

import com.github.fabriciolfj.api.product.dto.StockRequest;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class StockIntegration {

    @Inject
    @Channel("product")
    private Emitter<StockRequest> emitter;

    public void send(final StockRequest stockRequest) {
        log.info("Send to stock: {}", stockRequest);
        emitter.send(stockRequest);
    }
}
