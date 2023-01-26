package com.server.server.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.server.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    ArrayList<Product> findProductByRefAndDate(String ref, String date);

    ArrayList<Product> findProductByRefAndBrand(String ref, String brand);

    public ArrayList<Product> findAll();
}
