package com.bestpractice.api.controller;

import com.bestpractice.api.domain.model.ExceptionModel;
import com.bestpractice.api.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@ResponseBody
public class Advice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequest.class)
    public ExceptionModel badRequest() {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setStatus(400);
        exceptionModel.setError("Bad request");
        exceptionModel.setMessage("Bad request parameter");
        return exceptionModel;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnAuthorized.class)
    public ExceptionModel unAuthorized() {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setStatus(401);
        exceptionModel.setError("Unauthorized");
        exceptionModel.setMessage("Incorrect authentication info");
        return exceptionModel;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Forbidden.class)
    public ExceptionModel forbidden() {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setStatus(403);
        exceptionModel.setError("Forbidden");
        exceptionModel.setMessage("Not allowed");
        return exceptionModel;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ExceptionModel notFound01() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public ExceptionModel notFound02() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Conflict.class)
    public ExceptionModel conflict() {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setStatus(409);
        exceptionModel.setError("Conflict");
        exceptionModel.setMessage("Already exist email");
        return exceptionModel;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionModel serverError()   {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setStatus(500);
        exceptionModel.setError("Internal server error");
        exceptionModel.setMessage("Internal server error");
        return exceptionModel;
    }

    private ExceptionModel shareNotFound() {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setStatus(404);
        exceptionModel.setError("Not found");
        exceptionModel.setMessage("Not found path");
        return exceptionModel;
    }
}
