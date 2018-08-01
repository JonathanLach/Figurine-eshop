package com.figureshop.springmvc.dataAccess.util;

import com.figureshop.springmvc.dataAccess.entity.*;
import com.figureshop.springmvc.model.*;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntityMapper {
    private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    private final MapperFacade mapper = mapperFactory.getMapperFacade();

    public EntityMapper() {
        classMapBidirectional(UserEntity.class, User.class);
        classMapBidirectional(TranslationEntity.class, Translation.class);
        classMapBidirectional(PurchaseEntity.class, Purchase.class);
        classMapBidirectional(ProductEntity.class, Product.class);
        classMapBidirectional(LanguageEntity.class, Language.class);
    }

    private void classMapBidirectional(Class<?> c1, Class<?> c2) {
        mapperFactory.classMap(c1, c2);
        mapperFactory.classMap(c2, c1);
    }

    public User convertUserEntityToModel(UserEntity user) {
        return mapper.map(user, User.class);
    }

    public UserEntity convertUserModelToEntity(User user) {
        return mapper.map(user, UserEntity.class);
    }

    public Translation convertTranslationEntityToModel(TranslationEntity translation) {
        return mapper.map(translation, Translation.class);
    }

    public TranslationEntity convertTranslationModelToEntity(Translation translation) {
        return mapper.map(translation, TranslationEntity.class);
    }

    public Purchase convertPurchaseEntityToModel(PurchaseEntity purchase) {
        return mapper.map(purchase, Purchase.class);
    }

    public PurchaseEntity convertPurchaseModelToEntity(Purchase purchase) {
        return mapper.map(purchase, PurchaseEntity.class);
    }

    public Product convertProductEntityToModel(ProductEntity product) {
        return mapper.map(product, Product.class);
    }

    public ProductEntity convertProductModelToEntity(Product product) {
        return mapper.map(product, ProductEntity.class);
    }

    public List<Product> convertProductEntityListToModel(List<ProductEntity> products) {
        return mapper.mapAsList(products, Product.class);
    }

    public Language convertLanguageEntityToModel(LanguageEntity language) {
        return mapper.map(language, Language.class);
    }

    public LanguageEntity convertLanguageModelToEntity(Language language) {
        return mapper.map(language, LanguageEntity.class);
    }
}
