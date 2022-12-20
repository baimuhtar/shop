package com.baimuhtar.shop.controller;

import com.baimuhtar.shop.entity.Product;
import com.baimuhtar.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("")
public class MyRestController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
        Optional<Product> product = productService.getById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        HttpHeaders headers = new HttpHeaders();
        if (new Product() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST).getBody();
        }
        productService.save(product);
        return new ResponseEntity<>(product, headers, HttpStatus.OK).getBody();
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(int id) {
        productService.delete(id);
        return "Product with id " + id + "was deleted";
    }


}
