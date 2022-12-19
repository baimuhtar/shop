package com.baimuhtar.shop.service;

import com.baimuhtar.shop.entity.Product;
import com.baimuhtar.shop.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public Optional<Product> getById(int id) {
        log.info("IN ProductServiceImpl GetById {}", id);
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {

        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }
}
