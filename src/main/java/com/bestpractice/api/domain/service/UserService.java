package com.bestpractice.api.domain.service;

import com.bestpractice.api.domain.entity.User;
import com.bestpractice.api.domain.entity.UserKey;
import com.bestpractice.api.domain.repository.UserKeyRepository;
import com.bestpractice.api.domain.repository.UserRepository;
import com.bestpractice.api.exception.InternalServerError;
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
    public User getUserByIdAndUserUuid(Long id, String userUuid) {
        User user = this.userRepository.findByIdAndUuid(id, userUuid);
        user.setUsername("");
        user.setEmail("");
        user.setPassword("");
        return user;
    }

    public User getUserByEmail(String email) {
        User user;

        try {
            user = this.userRepository.findByEmail(email);
        } catch (Exception ex) {
            throw new InternalServerError();
        }

        return user;
    }

    public User generateUser(User user) {
        try {
            user = this.userRepository.save(user);
        } catch (Exception ex) {
            throw new InternalServerError();
        }

        return user;
    }

    public void generateUserKey(UserKey userKey) {
        try {
            this.userKeyRepository.save(userKey);
        } catch (Exception ex) {
            throw new InternalServerError();
        }
    }

    public UserKey getUserKey(Long userId) {
        UserKey userKey;

        try {
            userKey = this.userKeyRepository.findByUserId(userId);
        } catch (Exception ex) {
            throw new InternalServerError();
        }

        return userKey;
    }
}