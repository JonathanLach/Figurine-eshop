package com.figureshop.springmvc.service;

import com.figureshop.springmvc.model.Product;
import com.figureshop.springmvc.model.Translation;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> searchProductsByName(String searchTerm);

    Product getProductDetails(Long id);
}
