package com.figureshop.springmvc.dataAccess.repository;

import com.figureshop.springmvc.dataAccess.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {

}
