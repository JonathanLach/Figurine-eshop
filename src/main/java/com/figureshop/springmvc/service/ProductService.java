package com.figureshop.springmvc.service;

import com.figureshop.springmvc.model.Product;
import com.figureshop.springmvc.model.Translation;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    List<Translation> searchProductsByName(String searchTerm);

    Product getProductDetails(Long id);
}
