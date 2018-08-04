package com.figureshop.springmvc.service.impl;

import com.figureshop.springmvc.constants.PricingConstants;
import com.figureshop.springmvc.dataAccess.dao.ProductDAO;
import com.figureshop.springmvc.model.Product;
import com.figureshop.springmvc.model.Translation;
import com.figureshop.springmvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    private void setProductListWithRightTVAPrice(List<Product> products) {
        products.forEach(this::setProductWithRightTVAPrice);
    }

    private void setProductWithRightTVAPrice(Product p) {
        BigDecimal tvaAdditionalValue = p.getPrice().multiply(new BigDecimal(PricingConstants.TVA_RATE));
        p.setPrice(p.getPrice().add(tvaAdditionalValue));
        p.setPrice(p.getPrice().setScale(PricingConstants.PRICE_SCALE, RoundingMode.HALF_UP));
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products =  productDAO.getAllProducts();
        setProductListWithRightTVAPrice(products);
        return products;
    }

    public List<Product> searchProductsByName(String name) {
        return productDAO.getProductsByName(name);
    }

    @Override
    public Product getProductDetails(Long id) {
        return productDAO.getProductById(id);
    }
}
