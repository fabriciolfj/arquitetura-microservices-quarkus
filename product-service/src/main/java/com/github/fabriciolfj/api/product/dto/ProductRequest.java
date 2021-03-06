package com.github.fabriciolfj.api.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String description;
    private BigDecimal price;
    private String category;
    private Integer stock;
}
