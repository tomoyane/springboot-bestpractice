package com.bestpractice.api.app.v1;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @ResponseBody
  @ResponseStatus(value = HttpStatus.CREATED)
  @PostMapping()
  public UserResponse createUser(
      @RequestBody @Validated UserRequest request,
      BindingResult bdResult) {
    if (bdResult.hasErrors()) {
      throw new BadRequest(bdResult.getObjectName());
    }
    return this.userService.generateUser(request);
  }
}