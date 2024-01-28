package com.bestpractice.api.domain.service;

import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.infrastrucuture.entity.User;

public interface UserService {
  User getUserByIdAndUserUuid(Long id, String userUuid);
  User getAuthenticatedUser(String email, String rawPw);
  AuthResponse generateToken(Long userId, String userUid);
  UserResponse generateUser(UserRequest request);
}
