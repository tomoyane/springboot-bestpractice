package com.bestpractice.api.common.exception;

public class Conflict extends RuntimeException {
  public Conflict() {
    super();
  }

  public Conflict(String msg) {
    super(msg);
  }

  public Conflict(Throwable cause) {
    super(cause);
  }

  public Conflict(String msg, Throwable cause) {
    super(msg, cause);
  }
}
