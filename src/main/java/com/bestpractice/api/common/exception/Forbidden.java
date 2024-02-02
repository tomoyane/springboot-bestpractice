package com.bestpractice.api.common.exception;

public class Forbidden extends RuntimeException {
  public Forbidden() {
    super();
  }

  public Forbidden(String msg) {
    super(msg);
  }

  public Forbidden(Throwable cause) {
    super(cause);
  }

  public Forbidden(String msg, Throwable cause) {
    super(msg, cause);
  }
}
