package com.figureshop.springmvc.dataAccess.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchase")
public class PurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date purchaseDate = new Date();

    @OneToMany(mappedBy = "purchase")
    private List<ProductItemEntity> productItems = new ArrayList<>();

    @ManyToOne
    private UserEntity buyer;

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

    public List<ProductItemEntity> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductItemEntity> productItems) {
        this.productItems = productItems;
    }

    public UserEntity getBuyer() {
        return buyer;
    }

    public void setBuyer(UserEntity buyer) {
        this.buyer = buyer;
    }
}
