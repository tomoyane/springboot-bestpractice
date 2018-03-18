package com.bestpractice.api.domain.repository;

import com.bestpractice.api.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    UserEntity findByIdAndUuid(Long id, String uuid);

    UserEntity save(UserEntity userEntity);

    UserEntity removeById(Long id);
}