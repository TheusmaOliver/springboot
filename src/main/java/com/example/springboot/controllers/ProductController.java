package com.example.springboot.controllers;

import com.example.springboot.dto.ProductRecordDto;
import com.example.springboot.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductRecordDto>> getAllProducts(){
        List<ProductRecordDto> productList = productService.getAllProducts();
        return ResponseEntity.ok().body(productList);
    }

    @PostMapping
    public ResponseEntity<Void> saveProduct(@RequestBody ProductRecordDto product){
        productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
