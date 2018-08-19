package com.figureshop.springmvc.service.impl;

import com.figureshop.springmvc.constants.PricingConstants;
import com.figureshop.springmvc.dataAccess.dao.ProductDAO;
import com.figureshop.springmvc.model.Product;
import com.figureshop.springmvc.model.ProductItem;
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

    @Override
    public List<Translation> getAllProducts(String lang) {
        List<Translation> products =  productDAO.getAllProducts(lang);
        return products;
    }

    public List<Translation> searchProductsByName(String name, String lang) {
        return productDAO.getProductsByName(name, lang);
    }

    @Override
    public Translation getProductDetails(Long id, String lang) {
        return productDAO.getProductById(id, lang);
    }

    @Override
    public BigDecimal getTotalPriceWithTVA(BigDecimal totalExclTVA) {
        BigDecimal newPrice = totalExclTVA.multiply(new BigDecimal(1).add(new BigDecimal(PricingConstants.TVA_RATE)));
        newPrice = newPrice.setScale(PricingConstants.PRICE_SCALE, BigDecimal.ROUND_UP);
        return newPrice;
    }

    @Override
    public BigDecimal getVATAmount(BigDecimal totalPrice) {
        return totalPrice.multiply(new BigDecimal(PricingConstants.TVA_RATE));
    }

    @Override
    public BigDecimal updateTotalPrice(ProductItem productItem, int qty, BigDecimal totalPrice) {
        int qtyDifference = productItem.getQuantity() - qty;
        if(qtyDifference > 0) {
            BigDecimal valueToSubtract = productItem.getTranslation().getProduct().getPrice().multiply(new BigDecimal(qtyDifference));
            productItem.setSubTotal(productItem.getSubTotal()
                    .subtract(valueToSubtract));
            return totalPrice.subtract(valueToSubtract);
        }
        else {
            qtyDifference = Math.abs(qtyDifference);
            BigDecimal valueToAdd = productItem.getTranslation().getProduct().getPrice().multiply(new BigDecimal(qtyDifference));
            productItem.setSubTotal(productItem.getSubTotal()
                    .add(valueToAdd));
            return totalPrice.add(valueToAdd);
        }
    }

    @Override
    public BigDecimal updateTotalPriceDown(BigDecimal totalPrice, BigDecimal subTotal) {
        return totalPrice.subtract(subTotal);
    }

    @Override
    public BigDecimal updateTotalPriceUp(BigDecimal totalPrice, BigDecimal subTotal, BigDecimal formerSubTotal) {
        return totalPrice.add(subTotal.subtract(formerSubTotal));
    }

    @Override
    public BigDecimal getSubTotal(BigDecimal price, int qty) {
        return price.multiply(new BigDecimal(qty));
    }


}
