package com.bestpractice.api.domain.service;

import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.infrastrucuture.entity.User;

public interface UserService {
  User getUserById(String id);
  User getAuthenticatedUser(String email, String rawPw);
  UserResponse generateUser(UserRequest request);
}
