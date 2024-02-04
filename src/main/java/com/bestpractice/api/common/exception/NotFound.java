package com.bestpractice.api.common.exception;

public class NotFound extends RuntimeException {
  public NotFound() {
    super();
  }

  public NotFound(String msg) {
    super(msg);
  }

  public NotFound(Throwable cause) {
    super(cause);
  }

  public NotFound(String msg, Throwable cause) {
    super(msg, cause);
  }

}
