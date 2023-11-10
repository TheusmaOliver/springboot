package com.example.springboot.controllers;

import com.example.springboot.dto.ProductRecordDto;
import com.example.springboot.entities.Product;
import com.example.springboot.services.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id")UUID id) {
       Optional<ProductRecordDto> product = productService.getOneProduct(id);
        return product.<ResponseEntity<Object>>map(productRecordDto -> ResponseEntity.status(HttpStatus.OK).body(productRecordDto)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!"));
    }

    @PostMapping
    public ResponseEntity<ProductRecordDto> saveProduct(@RequestBody @Valid ProductRecordDto productDto){
        var newProduct = new Product(productDto);
        BeanUtils.copyProperties(productDto, newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(newProduct));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id")UUID id,@RequestBody @Valid ProductRecordDto productDto){
        Optional<ProductRecordDto> findProduct = productService.getOneProduct(id);
        if(findProduct.isPresent()){
            var getProduct = findProduct.get();
            var newProduct = new Product(getProduct);
            newProduct.setId(id);
            newProduct.setName(productDto.name());
            newProduct.setPrice(productDto.price());
            return ResponseEntity.status(HttpStatus.OK).body(productService.saveProduct(newProduct));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id")UUID id){
        productService.deleteProduct(String.valueOf(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
