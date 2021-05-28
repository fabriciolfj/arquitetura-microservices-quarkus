package com.github.fabriciolfj.core.service;

import com.github.fabriciolfj.core.entity.Stock;
import com.github.fabriciolfj.core.port.in.StockIn;
import com.github.fabriciolfj.core.repository.StockRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class StockService implements StockIn {

    @Inject
    private StockRepository stockRepository;

    @Transactional(Transactional.TxType.REQUIRED)
    public void save(final Stock stock) {
        stockRepository.persist(stock);
    }

    @Override
    @Transactional(Transactional.TxType.NEVER)
    public Stock getStock(final String code) {
        return stockRepository.findLast(code);
    }
}
