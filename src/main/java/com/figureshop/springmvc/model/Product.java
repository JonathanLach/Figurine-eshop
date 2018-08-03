package com.figureshop.springmvc.model;

import com.figureshop.springmvc.dataAccess.entity.TranslationEntity;
import org.apache.commons.lang3.ArrayUtils;
import sun.misc.BASE64Encoder;

import java.util.List;

public class Product {
    private Long id;

    private List<TranslationEntity> translations;

    private String description;

    private Byte[] picture;

    private String base64Picture;

    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<TranslationEntity> getTranslations() {
        return translations;
    }

    public void setTranslations(List<TranslationEntity> translations) {
        this.translations = translations;
    }

    public Byte[] getPicture() {
        return picture;
    }

    public void setPicture(Byte[] picture) {
        this.picture = picture;
    }

    public String getBase64Picture() {
        return base64Picture;
    }

    public void setBase64Picture(String base64Picture) {
        this.base64Picture = base64Picture;
    }
}
