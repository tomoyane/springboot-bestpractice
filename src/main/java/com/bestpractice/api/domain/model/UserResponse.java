package com.bestpractice.api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {
  @JsonProperty("id")
  private final String id;
  @JsonProperty("username")
  private final String username;
  @JsonProperty("email")
  private final String email;

  public UserResponse(String id, String username, String email) {
    this.id = id;
    this.username = username;
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }
}
