package com.figureshop.springmvc.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartInfo implements Serializable {
    private int size;
    private BigDecimal totalPrice;
    private BigDecimal totalPriceTVA;
    private String paypalUpdate;

    public String getPaypalUpdate() {
        return paypalUpdate;
    }

    public void setPaypalUpdate(String paypalUpdate) {
        this.paypalUpdate = paypalUpdate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
