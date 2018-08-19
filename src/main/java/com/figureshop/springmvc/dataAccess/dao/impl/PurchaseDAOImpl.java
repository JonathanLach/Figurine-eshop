package com.figureshop.springmvc.dataAccess.dao.impl;

import com.figureshop.springmvc.dataAccess.dao.PurchaseDAO;
import com.figureshop.springmvc.dataAccess.repository.PurchaseRepository;
import com.figureshop.springmvc.dataAccess.util.EntityMapper;
import com.figureshop.springmvc.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PurchaseDAOImpl implements PurchaseDAO {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private EntityMapper entityMapper;

    public void savePurchase(Purchase purchase) {
        purchaseRepository.save(entityMapper.convertPurchaseModelToEntity(purchase));
    }
}
