package com.figureshop.springmvc.service.impl;

import com.figureshop.springmvc.constants.PricingConstants;
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
    public void getVATAmount() {
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

        qty = 19;

        newTotalPrice  = new BigDecimal(201.02).setScale(2, RoundingMode.HALF_UP);

        assertTrue(newTotalPrice.compareTo(productService.updateTotalPrice(productItem, qty, totalPrice).setScale(2, BigDecimal.ROUND_HALF_UP)) == 0);
    }

    @Test
    public void updateTotalPriceDown() {
        BigDecimal totalPrice = new BigDecimal(220);
        BigDecimal subTotal = new BigDecimal(20);
        BigDecimal newTotal = new BigDecimal(200).setScale(2, RoundingMode.HALF_UP);
        assertTrue(newTotal.compareTo(productService.updateTotalPriceDown(totalPrice, subTotal).setScale(2, RoundingMode.HALF_UP)) == 0);

        totalPrice = new BigDecimal(112.44);
        subTotal = new BigDecimal(110.22);
        newTotal = new BigDecimal(2.22).setScale(2, RoundingMode.HALF_UP);

        assertTrue(newTotal.compareTo(productService.updateTotalPriceDown(totalPrice, subTotal).setScale(2, RoundingMode.HALF_UP)) == 0);
    }

    @Test
    public void updateTotalPriceUp() {
        BigDecimal totalPrice = new BigDecimal(222.22);
        BigDecimal subTotal = new BigDecimal(112.22);
        BigDecimal formerSubTotal = new BigDecimal(111.11);

        BigDecimal newTotal = new BigDecimal(223.33).setScale(PricingConstants.PRICE_SCALE, RoundingMode.HALF_UP);

        assertTrue(newTotal.compareTo(productService.updateTotalPriceUp(totalPrice, subTotal, formerSubTotal).setScale(PricingConstants.PRICE_SCALE, RoundingMode.HALF_UP)) == 0);

        totalPrice = new BigDecimal(929);
        subTotal = new BigDecimal(125);
        formerSubTotal = new BigDecimal(25);

        newTotal = new BigDecimal(1029).setScale(PricingConstants.PRICE_SCALE, RoundingMode.HALF_UP);

        assertTrue(newTotal.compareTo(productService.updateTotalPriceUp(totalPrice, subTotal, formerSubTotal).setScale(PricingConstants.PRICE_SCALE, RoundingMode.HALF_UP)) == 0);
    }

    @Test
    public void getSubTotal() {

        BigDecimal price = new BigDecimal(20.98);
        int qty = 15;
        BigDecimal subTotal = new BigDecimal(314.7).setScale(2, BigDecimal.ROUND_HALF_UP);
        assertTrue(subTotal.compareTo(productService.getSubTotal(price, qty).setScale(2, BigDecimal.ROUND_HALF_UP)) == 0);

        qty = 22;
        price = new BigDecimal(40);
        subTotal = new BigDecimal(880).setScale(2, RoundingMode.HALF_UP);
        assertTrue(subTotal.compareTo(productService.getSubTotal(price, qty).setScale(2, BigDecimal.ROUND_HALF_UP)) == 0);

    }
}