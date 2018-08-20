package com.figureshop.springmvc.model;

import com.figureshop.springmvc.dataAccess.entity.ProductEntity;
import com.figureshop.springmvc.dataAccess.entity.ProductItemEntity;
import com.figureshop.springmvc.dataAccess.entity.PurchaseEntity;
import com.figureshop.springmvc.dataAccess.entity.UserEntity;

import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Purchase implements Serializable {
    private Long id;
    private Date purchaseDate;
    private List<ProductItem> productItems = new ArrayList<>();
    private User buyer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
}
