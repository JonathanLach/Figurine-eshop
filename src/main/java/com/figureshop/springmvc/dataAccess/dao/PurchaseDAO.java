package com.figureshop.springmvc.dataAccess.dao;

import com.figureshop.springmvc.model.Purchase;

public interface PurchaseDAO {
    void savePurchase(Purchase purchase);
}
