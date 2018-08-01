package com.figureshop.springmvc.model;

import com.figureshop.springmvc.dataAccess.entity.ProductEntity;
import com.figureshop.springmvc.dataAccess.entity.UserEntity;

import java.util.Date;

public class Purchase {
    private Long id;
    private Long purchaseNumber;
    private Date purchaseDate;
    private Integer quantity;
    private Product productBought;
    private User buyer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(Long purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProductBought() {
        return productBought;
    }

    public void setProductBought(Product productBought) {
        this.productBought = productBought;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
}
