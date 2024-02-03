package com.bestpractice.api.app.v1;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @RequestMapping(method= RequestMethod.OPTIONS)
  public ResponseEntity<Object> optionsAuth() {
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Allow", "POST,OPTIONS");
    responseHeaders.set("Access-Control-Allow-Origin", "*");

    return ResponseEntity.ok()
        .headers(responseHeaders)
        .body(null);
  }

  @ResponseBody
  @ResponseStatus(value = HttpStatus.OK)
  @PostMapping(value = "/email-login")
  public AuthResponse login(
      @RequestBody @Validated AuthByEmailRequest request,
      BindingResult bdResult) {

    if (bdResult.hasErrors()) {
      throw new BadRequest(bdResult.getObjectName());
    }
    return this.authService.login(request.getEmail(), request.getPassword());
  }

  @ResponseBody
  @ResponseStatus(value = HttpStatus.OK)
  @PostMapping(value = "/refreshtoken-login")
  public AuthResponse login(
      @RequestBody @Validated AuthByRefreshTokenRequest request,
      BindingResult bdResult) {

    if (bdResult.hasErrors()) {
      throw new BadRequest(bdResult.getObjectName());
    }
    return this.authService.login(request.getRefreshToken());
  }
}