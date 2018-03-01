package com.bestpractice.api.service;

import com.bestpractice.api.domain.entity.UserEntity;
import com.bestpractice.api.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RdbmsService {

    private final UserRepository userRepository;

    public RdbmsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUser(Long id) {
        return userRepository.findById(id);
    }

    public void generateUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }
}
