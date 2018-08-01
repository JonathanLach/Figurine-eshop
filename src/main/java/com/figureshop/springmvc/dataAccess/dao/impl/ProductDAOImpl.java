package com.figureshop.springmvc.dataAccess.dao.impl;

import com.figureshop.springmvc.dataAccess.dao.ProductDAO;
import com.figureshop.springmvc.dataAccess.repository.ProductRepository;
import com.figureshop.springmvc.dataAccess.util.EntityMapper;
import com.figureshop.springmvc.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public List<Product> getAllProducts() {
        return entityMapper.convertProductEntityListToModel(productRepository.findAll());
    }
}
