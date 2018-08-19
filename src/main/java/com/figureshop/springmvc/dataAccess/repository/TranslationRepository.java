package com.figureshop.springmvc.dataAccess.repository;

import com.figureshop.springmvc.dataAccess.entity.ProductEntity;
import com.figureshop.springmvc.dataAccess.entity.TranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public interface TranslationRepository extends JpaRepository<TranslationEntity, Long> {

   @Query("SELECT t FROM TranslationEntity t INNER JOIN t.language lang WHERE lang.isoCode = ?1")
   List<TranslationEntity> findAllByLanguage(String lang);

   @Query("SELECT t FROM TranslationEntity t INNER JOIN t.language lang WHERE t.productName LIKE CONCAT('%', :searchTerm,'%') AND lang.isoCode = :lang")
   List<TranslationEntity> findByNameContaining(@Param("searchTerm") String name, @Param("lang") String lang);

   @Query("SELECT t from TranslationEntity t INNER JOIN t.product prod INNER JOIN t.language lang WHERE prod.id = ?1 AND lang.isoCode = ?2")
   TranslationEntity findProductDetails(Long id, String lang);
}
