package com.baimuhtar.shop.service;

import com.baimuhtar.shop.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getById(int id);

    Product save(Product product);

    void delete(int id);

    List<Product> getAll();
}
