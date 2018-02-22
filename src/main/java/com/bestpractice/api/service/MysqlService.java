package com.bestpractice.api.service;

import com.bestpractice.api.domain.entity.Users;
import com.bestpractice.api.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class MysqlService {

    private final UserRepository userRepository;

    public MysqlService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users getUser(Long id) {
        return userRepository.findUsersById(id);
    }
}
