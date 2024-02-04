package com.bestpractice.api.domain.service;

import com.bestpractice.api.domain.model.AuthResponse;

public interface AuthService {
  AuthResponse login(String email, String password);
  AuthResponse login(String refreshToken);
}
