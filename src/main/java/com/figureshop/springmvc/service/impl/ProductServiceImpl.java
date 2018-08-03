package com.figureshop.springmvc.service.impl;

import com.figureshop.springmvc.constants.PricingConstants;
import com.figureshop.springmvc.dataAccess.dao.ProductDAO;
import com.figureshop.springmvc.model.Product;
import com.figureshop.springmvc.model.Translation;
import com.figureshop.springmvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    private void setProductListWithRightTVAPrice(List<Product> products) {
        products.forEach(this::setProductWithRightTVAPrice);
    }

    private void setProductWithRightTVAPrice(Product p) {
        Double tvaAdditionalValue = PricingConstants.TVA_RATE * p.getPrice();
        p.setPrice(p.getPrice() + tvaAdditionalValue);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products =  productDAO.getAllProducts();
        setProductListWithRightTVAPrice(products);
        return products;
    }

    @Override
    public List<Translation> searchProductsByName(String searchTerm) {
        List<Translation> translations = productDAO.searchProductsByName(searchTerm);
        translations.forEach(t -> setProductWithRightTVAPrice(t.getProduct()));
        return translations;
    }

    @Override
    public Product getProductDetails(Long id) {
        return productDAO.getProductById(id);
    }
}
