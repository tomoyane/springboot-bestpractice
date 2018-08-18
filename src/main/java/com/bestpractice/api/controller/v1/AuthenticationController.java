package com.bestpractice.api.controller.v1;

import com.bestpractice.api.common.config.PwEncoderConfig;
import com.bestpractice.api.common.util.Util;
import com.bestpractice.api.domain.entity.User;
import com.bestpractice.api.domain.entity.UserKey;
import com.bestpractice.api.exception.BadRequest;
import com.bestpractice.api.exception.Conflict;
import com.bestpractice.api.domain.service.JsonWebTokenService;
import com.bestpractice.api.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
    public Map<String, String> generateUser(@RequestBody @Validated User user, BindingResult bdResult) {

        if (bdResult.hasErrors()) {
            throw new BadRequest();
        }

        if (this.userService.getUserByEmail(user.getEmail()) != null) {
            throw new Conflict();
        }

        String userUuid = UUID.randomUUID().toString();

        user.setPassword(this.pwEncoderConfig.passwordEncoder().encode(user.getPassword()));
        user.setUuid(userUuid);

        Long userId = this.userService.generateUser(user).getId();

        UserKey userKey = new UserKey();
        userKey.setTokenType("Bearer");
        userKey.setToken(this.jwtService.generateJwt(userId, 365, userUuid));
        userKey.setRefreshToken(this.jwtService.generateJwt(userId, 365, userUuid));
        userKey.setExpiresAt(Util.calculateDate());
        userKey.setUserId(userId);

        this.userService.generateUserKey(userKey);
        return Collections.singletonMap("message", "ok.");
    }

    @PostMapping(value = "/token")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, UserKey> issueToken(@RequestBody @Validated User body, BindingResult bdResult) {

        if (bdResult.hasErrors()) {
            throw new BadRequest();
        }

        User user = this.userService.getUserByEmail(body.getEmail());
        if (user == null) {
            throw new BadRequest();
        }

        if (!this.pwEncoderConfig.passwordEncoder().matches(user.getPassword(), user.getPassword())) {
            throw new BadRequest();
        }

        UserKey userKey = this.userService.getUserKey(user.getId());
        return Collections.singletonMap("key", userKey);
    }
}