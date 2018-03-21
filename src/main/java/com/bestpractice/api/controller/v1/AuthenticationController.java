package com.bestpractice.api.controller.v1;

import com.bestpractice.api.common.config.PwEncoderConfig;
import com.bestpractice.api.common.util.Util;
import com.bestpractice.api.domain.entity.UserEntity;
import com.bestpractice.api.domain.entity.UserKeyEntity;
import com.bestpractice.api.exception.Exception400;
import com.bestpractice.api.exception.Exception409;
import com.bestpractice.api.service.JsonWebTokenService;
import com.bestpractice.api.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthenticationController {

    private final UserService userService;
    private final JsonWebTokenService jwtService;
    private final PwEncoderConfig pwEncoderConfig;

    public AuthenticationController(UserService userService, JsonWebTokenService jwtService, PwEncoderConfig pwEncoderConfig) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.pwEncoderConfig = pwEncoderConfig;
    }

    @PostMapping
    public Map<String, UserKeyEntity> generateUser(@RequestBody @Validated UserEntity userEntity, BindingResult bdResult) {

        if (bdResult.hasErrors()) {
            throw new Exception400();
        }

        if (userService.checkUserByEmail(userEntity.getEmail()) != null) {
            throw new Exception409();
        }

        String userUuid = UUID.randomUUID().toString();

        userEntity.setPassword(pwEncoderConfig.passwordEncoder().encode(userEntity.getPassword()));
        userEntity.setUuid(userUuid);

        Long userId = userService.generateUser(userEntity).getId();

        Key key = jwtService.generateSignature();
        UserKeyEntity userKeyEntity = new UserKeyEntity();
        userKeyEntity.setTokenType("Bearer");
        userKeyEntity.setToken(jwtService.generateJwt(key, userId, 365, userUuid));
        userKeyEntity.setRefreshToken(jwtService.generateJwt(key, userId, 365, userUuid));
        userKeyEntity.setExpiresAt(Util.calculateDate());
        userKeyEntity.setUserId(userId);

        userService.generateUserKey(userKeyEntity);

        Map<String, UserKeyEntity> map = new HashMap<>();
        map.put("key", userKeyEntity);

        return map;
    }
}