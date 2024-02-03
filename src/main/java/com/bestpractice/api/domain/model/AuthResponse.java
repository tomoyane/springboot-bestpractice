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

  @JsonProperty("expired_at")
  private final Date expiresAt;

  public AuthResponse(String tokenType, String token, String refreshToken, Date expiresAt) {
    this.tokenType = tokenType;
    this.token = token;
    this.refreshToken = refreshToken;
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

  public Date getExpiresAt() {
    return expiresAt;
  }
}
