package com.figureshop.springmvc.service.impl;

import com.figureshop.springmvc.model.Product;
import com.figureshop.springmvc.model.ProductItem;
import com.figureshop.springmvc.model.Translation;
import com.figureshop.springmvc.service.ProductService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

public class ProductServiceImplTest {

    private ProductService productService = new ProductServiceImpl();

    @Test
    public void getTotalPriceWithTVA() {
        BigDecimal totalPrice = new BigDecimal(98.23);
        BigDecimal totalPriceTVA = new BigDecimal(118.8583).setScale(2, BigDecimal.ROUND_HALF_UP);
        assertTrue(totalPriceTVA.compareTo(productService.getTotalPriceWithTVA(totalPrice).setScale(2, BigDecimal.ROUND_HALF_UP)) == 0);
    }

    @Test
    public void getVATAmountOk() {
        BigDecimal totalPrice = new BigDecimal(15.29);
        BigDecimal tvaAmount = new BigDecimal(3.2109).setScale(2, RoundingMode.HALF_UP);
        assertTrue(tvaAmount.compareTo(productService.getVATAmount(totalPrice).setScale(2, RoundingMode.HALF_UP)) == 0);

        totalPrice = new BigDecimal(38.28);
        tvaAmount = new BigDecimal(8.0388).setScale(2, RoundingMode.HALF_UP);

        assertTrue(tvaAmount.compareTo(productService.getVATAmount(totalPrice).setScale(2, RoundingMode.HALF_UP)) == 0);
    }

    @Test
    public void updateTotalPrice() {

        Translation translation = new Translation();
        Product product = new Product();
        product.setPrice(new BigDecimal(10.58));
        translation.setProduct(product);

        ProductItem productItem = new ProductItem();
        productItem.setTranslation(translation);
        productItem.setQuantity(18);
        productItem.setSubTotal(new BigDecimal(190.44));
        int qty = 17;
        BigDecimal totalPrice = productItem.getSubTotal();

        BigDecimal newTotalPrice = new BigDecimal(179.86).setScale(2, BigDecimal.ROUND_HALF_UP);

        assertTrue(newTotalPrice.compareTo(productService.updateTotalPrice(productItem, qty, totalPrice).setScale(2, BigDecimal.ROUND_HALF_UP)) == 0);


    }

    @Test
    public void updateTotalPriceDown() {
    }

    @Test
    public void updateTotalPriceUp() {
    }

    @Test
    public void getSubTotal() {
    }
}