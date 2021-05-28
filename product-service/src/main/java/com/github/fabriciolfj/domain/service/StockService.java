package com.github.fabriciolfj.domain.service;

import com.github.fabriciolfj.api.product.dto.ProductRequest;
import com.github.fabriciolfj.api.product.mapper.StockMapper;
import com.github.fabriciolfj.domain.integration.http.StockHttp;
import com.github.fabriciolfj.domain.integration.http.dto.StockResponse;
import com.github.fabriciolfj.domain.integration.mensageria.StockIntegration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class StockService {

    @Inject
    private StockMapper stockMapper;
    @Inject
    private StockIntegration stockIntegration;
    @RestClient
    @Inject
    private StockHttp stockHttp;

    public void publisher(final ProductRequest request, final String code) {
        final var stock = stockMapper.toRequest(request, code);
        log.info("Stock: {}", stock);

        stockIntegration.send(stock);
    }

    public Integer getStock(final String code) {
        final StockResponse stock = stockHttp.getStock(code);
        return stock.getBalance();
    }
}
