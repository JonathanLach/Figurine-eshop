package com.figureshop.springmvc.dataAccess.entity;

import com.figureshop.springmvc.model.Translation;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "product")
    private List<TranslationEntity> translations;

    @Column(nullable = false)
    private Integer availableQuantity;

    private String description;

    @Lob
    @Column(nullable = false)
    private Byte[] picture;

    @Column(nullable = false)
    private Double exclTaxPrice;

    @OneToMany(mappedBy = "productBought")
    private List<PurchaseEntity> purchases;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte[] getPicture() {
        return picture;
    }

    public void setPicture(Byte[] picture) {
        this.picture = picture;
    }

    public Double getExclTaxPrice() {
        return exclTaxPrice;
    }

    public void setExclTaxPrice(Double exclTaxPrice) {
        this.exclTaxPrice = exclTaxPrice;
    }
}
