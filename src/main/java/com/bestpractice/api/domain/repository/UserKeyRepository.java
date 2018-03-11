package com.bestpractice.api.domain.repository;

import com.bestpractice.api.domain.entity.UserKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserKeyRepository extends JpaRepository<UserKeyEntity, Long> {

    UserKeyEntity findByToken(String token);

    UserKeyEntity save(UserKeyEntity userKeyEntity);

}