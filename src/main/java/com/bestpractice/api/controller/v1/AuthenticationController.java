package com.bestpractice.api.controller.v1;

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
    public Map<String, UserKeyEntity> postUser(@RequestBody UserEntity userEntity) {

//        if (result.hasErrors()) {
//            throw new Exception400();
//        }

        if (userService.checkUserByEmail(userEntity.getEmail()) != null) {
            throw new Exception409();
        }

        Long userId = userService.generateUser(userEntity).getId();

        UserKeyEntity userKeyEntity = new UserKeyEntity();
        userKeyEntity.setToken(jwtService.generateJwt(userId, 100));
        userKeyEntity.setRefreshToken(jwtService.generateJwt(userId, 200));
        userKeyEntity.setUserId(userId);
        userService.generateUserKey(userKeyEntity);

        Map<String, UserKeyEntity> map = new HashMap<>();
        map.put("key", userKeyEntity);

        return map;
    }
}