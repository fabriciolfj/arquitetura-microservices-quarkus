package com.github.fabriciolfj.core.service;

import com.github.fabriciolfj.core.entity.Stock;
import com.github.fabriciolfj.core.repository.StockRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class StockService {

    @Inject
    private StockRepository stockRepository;

    @Transactional(Transactional.TxType.REQUIRED)
    public void save(final Stock stock) {
        stockRepository.persist(stock);
    }
}
