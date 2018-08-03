package com.figureshop.springmvc.dataAccess.util;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import com.figureshop.springmvc.dataAccess.entity.*;
import com.figureshop.springmvc.model.*;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.util.List;

@Component
public class EntityMapper {
    private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    private final MapperFacade mapper = mapperFactory.getMapperFacade();

    public EntityMapper() {
        classMapBidirectional(UserEntity.class, User.class);
        classMapBidirectional(TranslationEntity.class, Translation.class);
        classMapBidirectional(PurchaseEntity.class, Purchase.class);
        classMapBidirectional(LanguageEntity.class, Language.class);
        classMapBidirectional(ProductEntity.class, Product.class);
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

    public List<Translation> convertTranslationEntityListToModel(List<TranslationEntity> translations) {
        return mapper.mapAsList(translations, Translation.class);
    }

    public Purchase convertPurchaseEntityToModel(PurchaseEntity purchase) {
        return mapper.map(purchase, Purchase.class);
    }

    public PurchaseEntity convertPurchaseModelToEntity(Purchase purchase) {
        return mapper.map(purchase, PurchaseEntity.class);
    }

    public Product convertProductEntityToModel(ProductEntity product) {
        Product mappedProduct =  mapper.map(product, Product.class);
        if (product.getPicture() != null) {
            String base64picture = new BASE64Encoder().encode(ArrayUtils.toPrimitive(product.getPicture()));
            mappedProduct.setBase64Picture(base64picture);
        }
        return mappedProduct;
    }

    public ProductEntity convertProductModelToEntity(Product product) {
        return mapper.map(product, ProductEntity.class);
    }

    public List<Product> convertProductEntityListToModel(List<ProductEntity> products) {
        List<Product> mappedProducts =  mapper.mapAsList(products, Product.class);
        mappedProducts.forEach(p -> {
            if (p.getPicture() != null) {
                String base64picture = new BASE64Encoder().encode(ArrayUtils.toPrimitive(p.getPicture()));
                p.setBase64Picture(base64picture);
            }
        });
        return mappedProducts;
    }

    public Language convertLanguageEntityToModel(LanguageEntity language) {
        return mapper.map(language, Language.class);
    }

    public LanguageEntity convertLanguageModelToEntity(Language language) {
        return mapper.map(language, LanguageEntity.class);
    }
}
