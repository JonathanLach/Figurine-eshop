package com.figureshop.springmvc.business;

import com.figureshop.springmvc.dataAccess.dao.ProductDAO;
import com.figureshop.springmvc.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductManager {
    private final static Double TVA_RATE = 0.21;

    @Autowired
    private ProductDAO productDAO;

    public List<Product> getAllProducts() {
        List<Product> products =  productDAO.getAllProducts();
        products.forEach(p -> {
            Double tvaAdditionalValue = TVA_RATE * p.getExclTaxPrice();
            p.setExclTaxPrice(p.getExclTaxPrice() + tvaAdditionalValue);
        });
        return products;
    }
}
