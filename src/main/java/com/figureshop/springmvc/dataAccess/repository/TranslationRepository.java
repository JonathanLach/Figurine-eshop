package com.figureshop.springmvc.dataAccess.repository;

import com.figureshop.springmvc.dataAccess.entity.TranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TranslationRepository extends JpaRepository<TranslationEntity, Long> {
}
