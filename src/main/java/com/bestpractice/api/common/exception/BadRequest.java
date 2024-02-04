package com.bestpractice.api.common.exception;

public class BadRequest extends RuntimeException {
  public BadRequest() {
    super();
  }

  public BadRequest(String msg) {
    super(msg);
  }

  public BadRequest(Throwable cause) {
    super(cause);
  }

  public BadRequest(String msg, Throwable cause) {
    super(msg, cause);
  }
}
