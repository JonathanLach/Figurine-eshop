package com.figureshop.springmvc.dataAccess.entity;

import com.figureshop.springmvc.model.Translation;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "product_item")
public class ProductItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @OneToOne
    @JoinColumn(nullable = false)
    private TranslationEntity translation;

    @Column(nullable = false)
    private BigDecimal subTotal;

    @ManyToOne
    private PurchaseEntity purchase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PurchaseEntity getPurchase() {
        return purchase;
    }

    public void setPurchase(PurchaseEntity purchase) {
        this.purchase = purchase;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TranslationEntity getTranslation() {
        return translation;
    }

    public void setTranslation(TranslationEntity translation) {
        this.translation = translation;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
}
