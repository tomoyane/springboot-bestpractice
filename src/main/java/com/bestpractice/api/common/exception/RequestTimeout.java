package com.bestpractice.api.common.exception;

public class RequestTimeout extends RuntimeException {
  public RequestTimeout() {
    super();
  }

  public RequestTimeout(String msg) {
    super(msg);
  }

  public RequestTimeout(Throwable cause) {
    super(cause);
  }

  public RequestTimeout(String msg, Throwable cause) {
    super(msg, cause);
  }

}
