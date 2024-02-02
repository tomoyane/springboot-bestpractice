package com.bestpractice.api.common.exception;

public class ServiceUnavailable extends RuntimeException {
  public ServiceUnavailable() {
    super();
  }

  public ServiceUnavailable(String msg) {
    super(msg);
  }

  public ServiceUnavailable(Throwable cause) {
    super(cause);
  }

  public ServiceUnavailable(String msg, Throwable cause) {
    super(msg, cause);
  }

}
