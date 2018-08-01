package com.figureshop.springmvc.dataAccess.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchase")
public class PurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long purchaseNumber;

    @Column(nullable = false)
    private Date purchaseDate = new Date();

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    private ProductEntity productBought;
    @ManyToOne
    private UserEntity buyer;

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

    public ProductEntity getProductBought() {
        return productBought;
    }

    public void setProductBought(ProductEntity productBought) {
        this.productBought = productBought;
    }

    public UserEntity getBuyer() {
        return buyer;
    }

    public void setBuyer(UserEntity buyer) {
        this.buyer = buyer;
    }
}
