package com.github.fabriciolfj.adapter.out.integration.mapper;

import com.github.fabriciolfj.core.entity.Stock;
import com.github.fabriciolfj.adapter.out.integration.dto.StockMessage;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@ApplicationScoped
public class StockIntegrationMapper {

    private static final Integer INIT_EXIT = 0;

    public Stock toEntity(final StockMessage message) {
        return Stock
                .builder()
                .balance(message.getStock())
                .code(message.getCode())
                .entrance(message.getStock())
                .exit(INIT_EXIT)
                .dateMov(LocalDateTime.now())
                .build();
    }
}
