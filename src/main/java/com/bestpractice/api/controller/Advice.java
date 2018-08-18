package com.bestpractice.api.controller;

import com.bestpractice.api.domain.model.Exception;
import com.bestpractice.api.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class Advice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequest.class)
    public Exception badRequest() {
        Exception exception = new Exception();
        exception.setStatus(400);
        exception.setError("Bad request");
        exception.setMessage("Bad request parameter");
        return exception;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnAuthorized.class)
    public Exception unAuthorized() {
        Exception exception = new Exception();
        exception.setStatus(401);
        exception.setError("Unauthorized");
        exception.setMessage("Incorrect authentication info");
        return exception;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Forbidden.class)
    public Exception forbidden() {
        Exception exception = new Exception();
        exception.setStatus(403);
        exception.setError("Forbidden");
        exception.setMessage("Not allowed");
        return exception;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Exception notFound01() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public Exception notFound02() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Conflict.class)
    public Exception conflict() {
        Exception exception = new Exception();
        exception.setStatus(409);
        exception.setError("Conflict");
        exception.setMessage("Already exist email");
        return exception;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(java.lang.Exception.class)
    public Exception serverError()   {
        Exception exception = new Exception();
        exception.setStatus(500);
        exception.setError("Internal server error");
        exception.setMessage("Internal server error");
        return exception;
    }

    private Exception shareNotFound() {
        Exception exception = new Exception();
        exception.setStatus(404);
        exception.setError("Not found");
        exception.setMessage("Not found path");
        return exception;
    }
}
