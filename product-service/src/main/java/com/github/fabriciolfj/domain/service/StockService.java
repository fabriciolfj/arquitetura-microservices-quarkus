package com.github.fabriciolfj.domain.service;

import com.github.fabriciolfj.api.product.dto.ProductRequest;
import com.github.fabriciolfj.api.product.mapper.StockMapper;
import com.github.fabriciolfj.domain.integration.StockIntegration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class StockService {

    private final StockMapper stockMapper;
    private final StockIntegration stockIntegration;

    public void publisher(final ProductRequest request, final String code) {
        final var stock = stockMapper.toRequest(request, code);
        log.info("Stock: {}", stock);

        stockIntegration.send(stock);
    }
}
