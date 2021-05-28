package com.github.fabriciolfj.adapter.out.integration;

import com.github.fabriciolfj.adapter.out.integration.dto.StockMessage;
import com.github.fabriciolfj.adapter.out.integration.mapper.StockIntegrationMapper;
import com.github.fabriciolfj.core.service.StockService;
import io.smallrye.reactive.messaging.annotations.Blocking;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
@ApplicationScoped
@Slf4j
public class StockConsumer {

    @Inject
    private StockIntegrationMapper stockMapper;
    @Inject
    private StockService stockService;

    @Incoming("product")
    @Blocking
    public void receive(final StockMessage message) {
        log.info("Message receive: {}", message);
        final var stock = stockMapper.toEntity(message);
        stockService.save(stock);
    }
}
