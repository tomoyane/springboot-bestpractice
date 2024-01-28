package com.bestpractice.api.app.v1;

import com.bestpractice.api.domain.model.AuthRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.common.exception.BadRequest;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final UserService userService;

  public AuthenticationController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(value = "/registration")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<UserResponse> generateUser(
      @RequestBody @Validated UserRequest req,
      BindingResult bdResult) throws URISyntaxException {

    if (bdResult.hasErrors()) {
      throw new BadRequest();
    }
    UserResponse res = this.userService.generateUser(req);

    return ResponseEntity
        .created(new URI("/api/v1/auth/registration/" + res.getId()))
        .body(res);
  }

  @PostMapping(value = "/token")
  @ResponseStatus(HttpStatus.OK)
  public AuthResponse issueToken(
      @RequestBody @Validated AuthRequest req,
      BindingResult bdResult) {

    if (bdResult.hasErrors()) {
      throw new BadRequest();
    }

    User user = this.userService.getAuthenticatedUser(req.getEmail(), req.getPassword());
    return this.userService.generateToken(user.getId(), user.getUuid());
  }
}