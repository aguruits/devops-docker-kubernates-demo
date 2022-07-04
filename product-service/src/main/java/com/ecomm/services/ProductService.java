package com.ecomm.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomm.entities.Product;
import com.ecomm.repositories.ProductRepository;

@Service
@Transactional
public class ProductService {
	@Autowired
    ProductRepository productRepository;
	
	Logger log = LogManager.getLogger(ProductService.class);
    
    public List<Product> findAllProducts() {
        List<Product> products = productRepository.findAll();
       
        return products;
    }
    
    public Optional<Product> findProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional;
    }

    public Optional<Product> findProductByCode(String code) {
        Optional<Product> productOptional = productRepository.findByCode(code);
        return productOptional;
    }
    
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
    
    public void deleteProduct(Product product) {
        this.productRepository.delete(product);
    }
}
