package com.bestpractice.api.service;

import com.bestpractice.api.domain.entity.UserEntity;
import com.bestpractice.api.domain.entity.UserKeyEntity;
import com.bestpractice.api.domain.repository.UserKeyRepository;
import com.bestpractice.api.domain.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserKeyRepository userKeyRepository;

    public UserService(UserRepository userRepository, UserKeyRepository userKeyRepository) {
        this.userRepository = userRepository;
        this.userKeyRepository = userKeyRepository;
    }

    @Cacheable(cacheNames = "users",  key = "'users:' + #id")
    public UserEntity getUserByIdAndUserUuid(Long id, String userUuid) {
        UserEntity userEntity = userRepository.findByIdAndUuid(id, userUuid);
        userEntity.setUsername("");
        userEntity.setEmail("");
        userEntity.setPassword("");
        return userEntity;
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity generateUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public void generateUserKey(UserKeyEntity userKeyEntity) {
        userKeyRepository.save(userKeyEntity);
    }

    public UserKeyEntity getUserKey(Long userId) {
        return userKeyRepository.findByUserId(userId);
    }
}