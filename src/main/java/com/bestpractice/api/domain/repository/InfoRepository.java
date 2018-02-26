package com.bestpractice.api.domain.repository.postgres;

import com.bestpractice.api.domain.entity.Infos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoRepository extends JpaRepository<Infos, Long> {
    List<Infos> findAll();
}