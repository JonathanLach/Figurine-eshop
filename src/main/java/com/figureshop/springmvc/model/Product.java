package com.figureshop.springmvc.model;

import com.figureshop.springmvc.dataAccess.entity.TranslationEntity;

import java.util.List;

public class Product {
    private Long id;

    private List<TranslationEntity> translations;

    private Integer availableQuantity;

    private String description;

    private Byte[] picture;

    private Double exclTaxPrice;

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
