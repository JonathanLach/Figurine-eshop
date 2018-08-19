package com.figureshop.springmvc.service;

import com.figureshop.springmvc.model.Purchase;

public interface PurchaseService {
    void savePurchase(Purchase purchase, String username);
}
