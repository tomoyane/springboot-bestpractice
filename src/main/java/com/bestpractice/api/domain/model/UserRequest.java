package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

public class UserRequest {
  @JsonProperty("username")
  @NotNull
  private String username;

  @NotNull
  @Email
  @JsonProperty("email")
  private String email;

  @NotNull
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

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

  public User convert(String encodePw) {
    User user = new User();
    user.setUuid(UUID.randomUUID().toString());
    user.setPassword(encodePw);
    user.setEmail(this.email);
    user.setUsername(this.username);
    return user;
  }
}
