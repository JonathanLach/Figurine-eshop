package com.figureshop.springmvc.service;

import com.figureshop.springmvc.model.Product;
import com.figureshop.springmvc.model.ProductItem;
import com.figureshop.springmvc.model.Translation;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface ProductService {
    List<Translation> getAllProducts(String lang);

    List<Translation> searchProductsByName(String searchTerm, String lang);

    Translation getProductDetails(Long id, String lang);

    BigDecimal getTotalPriceWithTVA(BigDecimal totalExclTVA);

    BigDecimal getVATAmount(BigDecimal totalPrice);

    BigDecimal updateTotalPrice(ProductItem productItem, int qty, BigDecimal totalPrice);

    BigDecimal updateTotalPriceDown(BigDecimal totalPrice, BigDecimal subTotal);

    BigDecimal updateTotalPriceUp(BigDecimal totalPrice, BigDecimal subTotal, BigDecimal formerSubTotal);

    BigDecimal getSubTotal(BigDecimal price, int qty);
}
