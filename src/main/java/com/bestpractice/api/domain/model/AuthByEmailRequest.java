package com.bestpractice.api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

public class AuthByEmailRequest {
  @Email
  @NotNull
  @JsonProperty("email")
  private String email;

  @NotNull
  @JsonProperty("password")
  private String password;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}