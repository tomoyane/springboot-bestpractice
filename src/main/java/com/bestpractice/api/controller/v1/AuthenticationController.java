package com.bestpractice.api.controller.v1;

import com.bestpractice.api.common.config.PwEncoderConfig;
import com.bestpractice.api.common.util.Util;
import com.bestpractice.api.domain.entity.UserEntity;
import com.bestpractice.api.domain.entity.UserKeyEntity;
import com.bestpractice.api.exception.BadRequest;
import com.bestpractice.api.exception.Conflict;
import com.bestpractice.api.service.JsonWebTokenService;
import com.bestpractice.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final UserService userService;
    private final JsonWebTokenService jwtService;
    private final PwEncoderConfig pwEncoderConfig;

    public AuthenticationController(UserService userService, JsonWebTokenService jwtService, PwEncoderConfig pwEncoderConfig) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.pwEncoderConfig = pwEncoderConfig;
    }

    @PostMapping(value = "/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> generateUser(@RequestBody @Validated UserEntity userEntity, BindingResult bdResult) {

        if (bdResult.hasErrors()) {
            throw new BadRequest();
        }

        if (userService.getUserByEmail(userEntity.getEmail()) != null) {
            throw new Conflict();
        }

        String userUuid = UUID.randomUUID().toString();

        userEntity.setPassword(pwEncoderConfig.passwordEncoder().encode(userEntity.getPassword()));
        userEntity.setUuid(userUuid);

        Long userId = userService.generateUser(userEntity).getId();

        UserKeyEntity userKeyEntity = new UserKeyEntity();
        userKeyEntity.setTokenType("Bearer");
        userKeyEntity.setToken(jwtService.generateJwt(userId, 365, userUuid));
        userKeyEntity.setRefreshToken(jwtService.generateJwt(userId, 365, userUuid));
        userKeyEntity.setExpiresAt(Util.calculateDate());
        userKeyEntity.setUserId(userId);

        userService.generateUserKey(userKeyEntity);

        Map<String, String> map = new HashMap<>();
        map.put("message", "OK");

        return map;
    }

    @PostMapping(value = "/token")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, UserKeyEntity> issueToken(@RequestBody @Validated UserEntity userEntity, BindingResult bdResult) {

        if (bdResult.hasErrors()) {
            throw new BadRequest();
        }

        UserEntity user = userService.getUserByEmail(userEntity.getEmail());
        if (user == null) {
            throw new BadRequest();
        }

        if (!pwEncoderConfig.passwordEncoder().matches(userEntity.getPassword(), user.getPassword())) {
            throw new BadRequest();
        }

        UserKeyEntity userKeyEntity = userService.getUserKey(user.getId());

        Map<String, UserKeyEntity> map = new HashMap<>();
        map.put("key", userKeyEntity);

        return map;
    }
}