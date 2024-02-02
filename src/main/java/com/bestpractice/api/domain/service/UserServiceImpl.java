package com.bestpractice.api.domain.service;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.util.Util;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.common.exception.InternalServerError;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserPersistentRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
        UserPersistentRepository userRepository,
        PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Cacheable(cacheNames = "users",  key = "'users:' + #id")
    public User getUserByIdAndUserUuid(Long id, String userUuid) {
        User user = this.userRepository.findByIdAndUuid(id, userUuid);
        user.setUsername("");
        user.setEmail("");
        user.setPassword("");
        return user;
    }

    @Override
    public User getAuthenticatedUser(String email, String rawPw) {
        User user = getUserByEmail(email);
        if (user == null) {
            throw new UnAuthorized();
        }

        if (!this.passwordEncoder.matches(user.getPassword(), rawPw)) {
            throw new UnAuthorized();
        }
        return user;
    }

    @Override
    public AuthResponse generateToken(Long userId, String userUid) {

        return null;
//        return new AuthResponse(
//            "Bearer",
//            token,
//            rToken,
//            userId,
//            Util.calculateDate()
//        );
    }

    @Override
    public UserResponse generateUser(UserRequest request) {
        if (getUserByEmail(request.getEmail()) != null) {
            throw new Conflict();
        }

        User user = request.convert(this.passwordEncoder.encode(request.getPassword()));
        try {
            user = this.userRepository.insert(user);
        } catch (Exception ex) {
            throw new InternalServerError();
        }

        return new UserResponse(user.getId(), user.getUuid(), user.getUsername(), user.getEmail());
    }


    private User getUserByEmail(String email) {
        User user;

        try {
            user = this.userRepository.findByEmail(email);
        } catch (Exception ex) {
            throw new InternalServerError();
        }

        return user;
    }
}