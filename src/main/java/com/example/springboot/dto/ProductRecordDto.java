package com.example.springboot.dto;

import com.example.springboot.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRecordDto(@NotBlank String name, @NotNull BigDecimal price) {
    public ProductRecordDto(Product product){
        this(product.getName(), product.getPrice());
    }
}
