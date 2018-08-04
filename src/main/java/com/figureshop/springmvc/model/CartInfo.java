package com.figureshop.springmvc.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartInfo implements Serializable {
    private int size;
    private BigDecimal totalPrice;

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
}
