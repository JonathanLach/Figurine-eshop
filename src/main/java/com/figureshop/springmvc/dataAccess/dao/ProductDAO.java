package com.figureshop.springmvc.dataAccess.dao;

import com.figureshop.springmvc.model.Product;
import com.figureshop.springmvc.model.Translation;

import java.util.ArrayList;
import java.util.List;

public interface ProductDAO {

    List<Translation> getAllProducts(String lang);

    List<Translation> getProductsByName(String name, String lang);

    Translation getProductById(Long id, String lang);
}
