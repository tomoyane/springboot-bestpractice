package com.bestpractice.api.controller.v1;

import com.bestpractice.api.domain.entity.UserEntity;
import com.bestpractice.api.domain.entity.UserKeyEntity;
import com.bestpractice.api.exception.Exception400;
import com.bestpractice.api.exception.Exception409;
import com.bestpractice.api.service.JsonWebTokenService;
import com.bestpractice.api.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthenticationController {

    private final UserService userService;
    private final JsonWebTokenService jwtService;

    public AuthenticationController(UserService userService, JsonWebTokenService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping
    @PreAuthorize("permitAll")
    public Map<String, UserKeyEntity> postUser(@Validated @RequestBody UserEntity userEntity, BindingResult result) {

        if (result.hasErrors()) {
            throw new Exception400();
        }

        if (!userService.checkUserByEmail(userEntity.getEmail())) {
            throw new Exception409();
        }

        Long userId = userService.generateUser(userEntity).getId();
        String token = jwtService.generateJwt(userId, 100);
        String refreshToken = jwtService.generateJwt(userId, 200);

        UserKeyEntity userKeyEntity = userService.generateUserKey(userId, token, refreshToken);
        Map<String, UserKeyEntity> map = new HashMap<>();
        map.put("key", userKeyEntity);

        return map;
    }
}