package com.figureshop.springmvc.service.impl;

import com.figureshop.springmvc.dataAccess.dao.PurchaseDAO;
import com.figureshop.springmvc.dataAccess.dao.UserDAO;
import com.figureshop.springmvc.dataAccess.repository.PurchaseRepository;
import com.figureshop.springmvc.model.Purchase;
import com.figureshop.springmvc.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseDAO purchaseDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public void savePurchase(Purchase purchase, String username) {
        purchase.setBuyer(userDAO.getUserByUsername(username));
        purchaseDAO.savePurchase(purchase);
    }
}
