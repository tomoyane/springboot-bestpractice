package com.bestpractice.api.domain.repository;

import com.bestpractice.api.domain.entity.InfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoRepository extends JpaRepository<InfoEntity, Long> {
    List<InfoEntity> findAll();
}