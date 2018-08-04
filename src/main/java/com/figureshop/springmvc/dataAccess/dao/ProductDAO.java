package com.figureshop.springmvc.dataAccess.dao;

import com.figureshop.springmvc.model.Product;
import com.figureshop.springmvc.model.Translation;

import java.util.ArrayList;
import java.util.List;

public interface ProductDAO {

    List<Product> getAllProducts();

    List<Product> getProductsByName(String name);

    Product getProductById(Long id);
}
