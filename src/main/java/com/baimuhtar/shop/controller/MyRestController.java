package com.baimuhtar.shop.controller;

import com.baimuhtar.shop.entity.Product;
import com.baimuhtar.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class MyRestController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> showAllProduct() {

        List<Product> allProducts = productService.getAll();
        return allProducts;
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {

        Optional<Product> product = productService.getById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //
    @PostMapping("/products")
    public ResponseEntity<Product> addNewProduct(@RequestBody Product newProduct) {
//        Optional<Product> product1 = productService.save(newProduct);
        HttpHeaders headers = new HttpHeaders();
        if (newProduct == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        productService.save(newProduct);
        return new ResponseEntity<>(newProduct, headers, HttpStatus.CREATED);
    }

    //
    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST).getBody();
        }
        productService.save(product);
        return new ResponseEntity<Product>(product, headers, HttpStatus.OK).getBody();
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable int id) {

        productService.delete(id);
        return "Product with ID = " + id + " was deleted";
    }


}
