package com.example.springboot.services;

import com.example.springboot.dto.ProductRecordDto;
import com.example.springboot.entities.Product;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductRecordDto> getAllProducts(){
        return productRepository.findAll().stream().map(ProductRecordDto::new).toList();
    }

    public Optional<ProductRecordDto> getOneProduct(UUID id)  {
         return productRepository.findById(id).map(ProductRecordDto::new);
    }

    public ProductRecordDto saveProduct(Product product){
        productRepository.save(product);
        return new ProductRecordDto(product);
    }

    public void deleteProduct(String id){
        productRepository.deleteById(UUID.fromString(id));
        return;
    }
}
