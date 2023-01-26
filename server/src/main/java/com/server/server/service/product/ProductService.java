package com.server.server.service.product;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.server.model.Product;
import com.server.server.repository.ProductRepository;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository pRepo;

    @Override
    public ArrayList<Product> findProductByRefAndDate(String ref, String date) {
        return pRepo.findProductByRefAndDate(ref, date);
    }

    @Override
    public ArrayList<Product> findProductByRefAndBrand(String ref, String brand) {
        return pRepo.findProductByRefAndBrand(ref, brand);
    }

    @Override
    public ArrayList<Product> findAll() {
        return pRepo.findAll();
    }

    @Override
    public ArrayList<Product> findProductByClientId(ArrayList<Product> arr, Long id) {
        ArrayList<Product> res = new ArrayList<>();
        for (Product p : arr) {
            if (p.getClient().getId().equals(id))
                res.add(p);
        }

        return res;
    }
}
