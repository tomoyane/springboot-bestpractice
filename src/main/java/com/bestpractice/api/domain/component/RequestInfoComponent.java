package com.bestpractice.api.domain.component;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class RequestInfoComponent {
  private String userId;
  private String userEmail;
  private boolean isRefreshToken;
  private String path;
  private String httpMethod;
  private String requestId;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public boolean isRefreshToken() {
    return isRefreshToken;
  }

  public void setRefreshToken(boolean refreshToken) {
    isRefreshToken = refreshToken;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getHttpMethod() {
    return httpMethod;
  }

  public void setHttpMethod(String httpMethod) {
    this.httpMethod = httpMethod;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }
}