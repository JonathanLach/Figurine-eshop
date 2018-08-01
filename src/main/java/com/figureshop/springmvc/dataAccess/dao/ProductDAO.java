package com.figureshop.springmvc.dataAccess.dao;

import com.figureshop.springmvc.model.Product;

import java.util.List;

public interface ProductDAO {

    List<Product> getAllProducts();
}
