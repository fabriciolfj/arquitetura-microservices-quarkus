package com.github.fabriciolfj.core.repository;

import com.github.fabriciolfj.core.entity.Stock;
import com.github.fabriciolfj.core.exception.StockException;
import com.github.fabriciolfj.core.exceptions.StockNotFoundException;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StockRepository implements PanacheRepository<Stock> {

    public Stock findLast(final String code) {
        return find("Select s From Stock s where s.dateMov = (select max(t.dateMov) from Stock t where t.code = ?1)" +
                " and s.code = ?1", code)
                .firstResultOptional()
                .orElseThrow(() -> new StockNotFoundException("Stock not found, code: " + code));
    }
}
