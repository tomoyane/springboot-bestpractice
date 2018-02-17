package com.bestpractice.api.service;

import com.bestpractice.api.domain.entity.Users;
import com.bestpractice.api.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MysqlService {

    @Autowired
    UserRepository userRepository;

    public Users getUser(Long id) {
        return userRepository.findOne(id);
    }
}
