package com.bestpractice.api.domain.repository;

import com.bestpractice.api.domain.entity.UserKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserKeyRepository extends JpaRepository<UserKeyEntity, Long> {

    UserKeyEntity findByUserId(Long userId);

    UserKeyEntity save(UserKeyEntity userKeyEntity);

}