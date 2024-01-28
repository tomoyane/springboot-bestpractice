package com.bestpractice.api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {
  @JsonProperty("id")
  private final Long id;
  @JsonProperty("uid")
  private final String uId;
  @JsonProperty("username")
  private final String username;
  @JsonProperty("email")
  private final String email;

  public UserResponse(Long id, String uId, String username, String email) {
    this.id = id;
    this.uId = uId;
    this.username = username;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public String getuId() {
    return uId;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }
}
