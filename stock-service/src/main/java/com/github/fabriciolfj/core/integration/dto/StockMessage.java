package com.github.fabriciolfj.core.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMessage {

    private Integer stock;
    private String code;
}
