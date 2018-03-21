package com.bestpractice.api.controller;

import com.bestpractice.api.domain.model.ExceptionModel;
import com.bestpractice.api.exception.Exception400;
import com.bestpractice.api.exception.Exception401;
import com.bestpractice.api.exception.Exception403;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class Advice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception400.class)
    @ResponseBody
    public ExceptionModel badRequest() {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setStatus(400);
        exceptionModel.setError("Bad request");
        exceptionModel.setMessage("Bad request parameter");
        return exceptionModel;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(Exception401.class)
    @ResponseBody
    public ExceptionModel unAuthorized() {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setStatus(401);
        exceptionModel.setError("Unauthorized");
        exceptionModel.setMessage("Incorrect authentication info");
        return exceptionModel;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Exception403.class)
    @ResponseBody
    public ExceptionModel forbidden() {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setStatus(403);
        exceptionModel.setError("Forbidden");
        exceptionModel.setMessage("Not allowed");
        return exceptionModel;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ExceptionModel notFound() {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setStatus(404);
        exceptionModel.setError("Not found");
        exceptionModel.setMessage("");
        return exceptionModel;
    }
}
