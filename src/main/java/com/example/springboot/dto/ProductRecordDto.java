package com.example.springboot.dto;

import com.example.springboot.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRecordDto(UUID id,@NotBlank String name, @NotNull BigDecimal price) {
    public ProductRecordDto(Product product){
        this(product.getId(), product.getName(), product.getPrice());
    }
}

