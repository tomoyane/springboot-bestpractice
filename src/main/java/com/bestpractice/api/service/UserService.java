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

    public boolean checkUserByEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public UserEntity generateUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserKeyEntity generateUserKey(Long userId, String token, String refreshToken) {
        UserKeyEntity userKeyEntity = new UserKeyEntity();
        userKeyEntity.setToken(token);
        userKeyEntity.setRefreshToken(refreshToken);
        userKeyEntity.setUserId(userId);

        return userKeyRepository.save(userKeyEntity);
    }
}
