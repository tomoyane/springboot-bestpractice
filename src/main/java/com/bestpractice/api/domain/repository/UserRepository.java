package com.bestpractice.api.domain.repository;

import com.bestpractice.api.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAll();

    UserEntity findById(Long id);

    UserEntity save(UserEntity userEntity);

    UserEntity deleteById(Long id);
}