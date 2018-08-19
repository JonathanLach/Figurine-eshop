package com.figureshop.springmvc.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartUpdateInfo implements Serializable {
    private BigDecimal subTotal;
    private BigDecimal totalPrice;
    private BigDecimal totalPriceTVA;

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPriceTVA() {
        return totalPriceTVA;
    }

    public void setTotalPriceTVA(BigDecimal totalPriceTVA) {
        this.totalPriceTVA = totalPriceTVA;
    }
}
