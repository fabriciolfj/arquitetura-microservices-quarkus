package com.github.fabriciolfj.adapter.out.integration.dto;

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
