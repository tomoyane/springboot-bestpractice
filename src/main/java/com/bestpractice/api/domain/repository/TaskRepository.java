package com.bestpractice.api.domain.repository;

import com.bestpractice.api.domain.entity.InfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface InfoRepository extends JpaRepository<InfoEntity, Long> {
    List<InfoEntity> findAll();

    InfoEntity findById(Long id);

    InfoEntity save(InfoEntity infoEntity);

    void removeById(Long id);
}