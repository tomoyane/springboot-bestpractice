package com.bestpractice.api.service;

import com.bestpractice.api.domain.entity.UserEntity;
import com.bestpractice.api.domain.entity.UserKeyEntity;
import com.bestpractice.api.domain.repository.UserKeyRepository;
import com.bestpractice.api.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserKeyRepository userKeyRepository;

    public UserService(UserRepository userRepository, UserKeyRepository userKeyRepository) {
        this.userRepository = userRepository;
        this.userKeyRepository = userKeyRepository;
    }

    public UserEntity checkUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity generateUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserKeyEntity generateUserKey(UserKeyEntity userKeyEntity) {
        return userKeyRepository.save(userKeyEntity);
    }
}
