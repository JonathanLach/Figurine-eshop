package com.figureshop.springmvc.dataAccess.repository;

import com.figureshop.springmvc.dataAccess.entity.ProductEntity;
import com.figureshop.springmvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM TranslationEntity t INNER JOIN t.product p WHERE t.translation LIKE CONCAT('%', :searchTerm,'%')")
    List<ProductEntity> findByNameContaining(@Param("searchTerm") String name);
}
