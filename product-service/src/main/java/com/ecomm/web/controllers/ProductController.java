package com.ecomm.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.entities.Product;
import com.ecomm.exceptions.ProductNotFoundException;
import com.ecomm.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	Logger log = LogManager.getLogger(ProductController.class);
	
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> allProducts(HttpServletRequest request) {
        log.info("Finding all products");
        String auth_header = request.getHeader("AUTH_HEADER");
        log.info("AUTH_HEADER: "+auth_header);
        return productService.findAllProducts();
    }

    @GetMapping("/{productId}")
    public Product productById(@PathVariable Long productId) {
        log.info("Finding product by Id :" + productId);
        return productService.findProductById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with Id ["+productId+"] doesn't exist"));
    }
    
	/*
	 * @GetMapping("/{code}") public Product productByCode(@PathVariable String
	 * code) { log.info("Finding product by code :"+code); return
	 * productService.findProductByCode(code) .orElseThrow(() -> new
	 * ProductNotFoundException("Product with code ["+code+"] doesn't exist")); }
	 */
    
    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(Product product) {
    	log.info("add product");
        Product postDBProduct = productService.addProduct(product);
        log.info("added product information successfully!!!");
        return new ResponseEntity<Product>(postDBProduct, HttpStatus.OK);
    }
    
    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(Product product) {
    	log.info("update product");
    	Product postDBProduct =  productService.updateProduct(product);
    	log.info("update product information successfully!!!");
        return new ResponseEntity<Product>(postDBProduct, HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteProduct")
    public ResponseEntity<Product> deleteProduct(Product product) {
    	log.info("remove product");
        this.productService.deleteProduct(product);
        log.info("deleted product information successfully!!!");
        return new ResponseEntity<Product>(HttpStatus.OK);
    }
}
