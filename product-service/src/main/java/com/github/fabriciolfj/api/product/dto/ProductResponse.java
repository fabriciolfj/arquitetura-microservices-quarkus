package com.github.fabriciolfj.api.product.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {

    private String description;
    private BigDecimal price;
    private String code;
    private Integer stock;
}
