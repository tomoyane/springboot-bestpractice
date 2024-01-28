package com.bestpractice.api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;

public class AuthResponse {
  @NotNull
  @JsonProperty("token_type")
  private final String tokenType;

  @NotNull
  @JsonProperty("token")
  private final String token;

  @NotNull
  @JsonProperty("refresh_token")
  private final String refreshToken;

  @NotNull
  @JsonProperty("user_id")
  private final Long userId;

  @JsonProperty("expired_at")
  private final Date expiresAt;

  public AuthResponse(String tokenType, String token, String refreshToken, Long userId,
      Date expiresAt) {
    this.tokenType = tokenType;
    this.token = token;
    this.refreshToken = refreshToken;
    this.userId = userId;
    this.expiresAt = expiresAt;
  }

  public String getTokenType() {
    return tokenType;
  }

  public String getToken() {
    return token;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public Long getUserId() {
    return userId;
  }

  public Date getExpiresAt() {
    return expiresAt;
  }
}
