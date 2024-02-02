package com.bestpractice.api.common.exception;

public class UnAuthorized extends RuntimeException {
  public UnAuthorized() {
    super();
  }

  public UnAuthorized(String msg) {
    super(msg);
  }

  public UnAuthorized(Throwable cause) {
    super(cause);
  }

  public UnAuthorized(String msg, Throwable cause) {
    super(msg, cause);
  }

}
