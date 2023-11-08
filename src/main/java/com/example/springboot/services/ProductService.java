package com.example.springboot.services;

import com.example.springboot.dto.ProductRecordDto;
import com.example.springboot.entities.Product;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductRecordDto> getAllProducts(){
        return productRepository.findAll().stream().map(ProductRecordDto::new).toList();
    }

    public void saveProduct(ProductRecordDto product){
        Product newProduct = new Product(product);
        productRepository.save(newProduct);
        return;
    }
}
