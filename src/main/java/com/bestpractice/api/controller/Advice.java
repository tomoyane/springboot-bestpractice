package com.bestpractice.api.controller;

import com.bestpractice.api.domain.model.ExceptionModel;
import com.bestpractice.api.exception.Exception400;
import com.bestpractice.api.exception.Exception401;
import com.bestpractice.api.exception.Exception403;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class Advice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception400.class)
    @ResponseBody
    public ExceptionModel badRequest() {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setStatus(400);
        exceptionModel.setMessage("Bad request");
        return exceptionModel;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(Exception401.class)
    @ResponseBody
    public ExceptionModel unAuthorized() {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setStatus(401);
        exceptionModel.setMessage("Unauthorized");
        return exceptionModel;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ExceptionModel handleAuthenticationException() {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setStatus(401);
        exceptionModel.setMessage("Unauthorized");
        return exceptionModel;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Exception403.class)
    @ResponseBody
    public ExceptionModel forbidden() {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setStatus(403);
        exceptionModel.setMessage("Forbidden");
        return exceptionModel;
    }
}
