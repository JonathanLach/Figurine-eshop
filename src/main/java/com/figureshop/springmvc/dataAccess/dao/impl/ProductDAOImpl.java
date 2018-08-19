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
    public List<Translation> getAllProducts(String lang) {
        return entityMapper.convertTranslationEntityListToModel(translationRepository.findAllByLanguage(lang));
    }

    @Override
    public List<Translation> getProductsByName(String name, String lang) {
        return entityMapper.convertTranslationEntityListToModel(translationRepository.findByNameContaining(name, lang));
    }

    @Override
    public Translation getProductById(Long id, String lang) {
        return entityMapper.convertTranslationEntityToModel(translationRepository.findProductDetails(id, lang));
    }
}
