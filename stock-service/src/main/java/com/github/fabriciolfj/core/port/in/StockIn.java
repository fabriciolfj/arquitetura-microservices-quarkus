package com.github.fabriciolfj.core.port.in;

import com.github.fabriciolfj.core.entity.Stock;

public interface StockIn {

    Stock getStock(final String code);
}
