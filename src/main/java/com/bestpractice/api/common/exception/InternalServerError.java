package com.bestpractice.api.common.exception;

public class InternalServerError extends RuntimeException {
  public InternalServerError() {
    super();
  }

  public InternalServerError(String msg) {
    super(msg);
  }

  public InternalServerError(Throwable cause) {
    super(cause);
  }

  public InternalServerError(String msg, Throwable cause) {
    super(msg, cause);
  }

}
