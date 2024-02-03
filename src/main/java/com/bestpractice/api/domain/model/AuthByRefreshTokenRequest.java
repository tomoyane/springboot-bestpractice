package com.bestpractice.api.domain.model;

import javax.validation.constraints.NotNull;

public class AuthByRefreshTokenRequest {
  @NotNull
  private String refreshToken;

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}