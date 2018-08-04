package com.figureshop.springmvc.dataAccess.dao.impl;

import com.figureshop.springmvc.dataAccess.dao.ProductDAO;
import com.figureshop.springmvc.dataAccess.entity.TranslationEntity;
import com.figureshop.springmvc.dataAccess.repository.ProductRepository;
import com.figureshop.springmvc.dataAccess.repository.TranslationRepository;
import com.figureshop.springmvc.dataAccess.util.EntityMapper;
import com.figureshop.springmvc.model.Product;
import com.figureshop.springmvc.model.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public List<Product> getAllProducts() {
        return entityMapper.convertProductEntityListToModel(productRepository.findAll());
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return entityMapper.convertProductEntityListToModel(productRepository.findByNameContaining(name));
    }

    @Override
    public Product getProductById(Long id) {
        return entityMapper.convertProductEntityToModel(productRepository.findOne(id));
    }
}
