package com.bestpractice.api.domain.repository;

import com.bestpractice.api.domain.entity.Info;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Profile("!test")
public interface InfoRepository extends JpaRepository<Info, Long> {
    List<Info> findAll();

    Info findById(Long id);

    Info save(Info info);

    void removeById(Long id);
}
