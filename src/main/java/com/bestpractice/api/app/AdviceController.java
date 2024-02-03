package com.bestpractice.api.app;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequest.class)
    public ErrorResponse badRequest() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(400);
        res.setError("Bad request");
        res.setMessage("Bad request parameter");
        return res;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnAuthorized.class)
    public ErrorResponse unAuthorized() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(401);
        res.setError("Unauthorized");
        res.setMessage("Incorrect authentication info");
        return res;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Forbidden.class)
    public ErrorResponse forbidden() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(403);
        res.setError("Forbidden");
        res.setMessage("Not allowed");
        return res;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorResponse notFound01() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public ErrorResponse notFound02() {
        return shareNotFound();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Conflict.class)
    public ErrorResponse conflict() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(409);
        res.setError("Conflict");
        res.setMessage("Already exist data");
        return res;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse serverError(Exception e)   {
        e.printStackTrace();
        ErrorResponse res = new ErrorResponse();
        res.setStatus(500);
        res.setError("Internal server error");
        res.setMessage("Internal server error");
        return res;
    }

    private ErrorResponse shareNotFound() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(404);
        res.setError("Not found");
        res.setMessage("Not found path");
        return res;
    }
}
