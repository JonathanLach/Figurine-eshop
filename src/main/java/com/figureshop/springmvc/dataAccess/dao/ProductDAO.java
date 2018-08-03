package com.figureshop.springmvc.dataAccess.dao;

import com.figureshop.springmvc.model.Product;
import com.figureshop.springmvc.model.Translation;

import java.util.List;

public interface ProductDAO {

    List<Product> getAllProducts();

    List<Translation> searchProductsByName(String searchTerm);

    Product getProductById(Long id);
}
