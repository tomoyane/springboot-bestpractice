package com.bestpractice.api.exception;

import com.bestpractice.api.domain.config.ErrorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandler {

    @Autowired
    ErrorConfig errorConfig;

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @ResponseBody
    @ExceptionHandler(Exception400.class)
    public Map<String, Object> error400(Exception400 exception400) {
        logger.error(exception400.toString());

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("status", HttpStatus.BAD_REQUEST);
        errorMap.put("message", "");
        return errorMap;
    }

    @ResponseBody
    @ExceptionHandler(Exception401.class)
    public Map<String, Object> error401(Exception401 exception401) {
        logger.error(exception401.toString());

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("status", HttpStatus.UNAUTHORIZED);
        errorMap.put("message", "");
        return errorMap;
    }

    @ResponseBody
    @ExceptionHandler(Exception403.class)
    public Map<String, Object> error403(Exception403 exception403) {
        logger.error(exception403.toString());

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("status", HttpStatus.FORBIDDEN);
        errorMap.put("message", "");
        return errorMap;
    }

    @ResponseBody
    @ExceptionHandler(Exception404.class)
    public Map<String, Object> error404(Exception404 exception404) {
        logger.error(exception404.toString());

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("status", HttpStatus.NOT_FOUND);
        errorMap.put("message", "");
        return errorMap;
    }

    @ResponseBody
    @ExceptionHandler(Exception408.class)
    public Map<String, Object> error408(Exception408 exception408) {
        logger.error(exception408.toString());

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("status", HttpStatus.REQUEST_TIMEOUT);
        errorMap.put("message", "");
        return errorMap;
    }

    @ResponseBody
    @ExceptionHandler(Exception500.class)
    public Map<String, Object> error500(Exception500 exception500) {
        logger.error(exception500.toString());

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        errorMap.put("message", "");
        return errorMap;
    }

    @ResponseBody
    @ExceptionHandler(Exception503.class)
    public Map<String, Object> error503(Exception503 exception503) {
        logger.error(exception503.toString());

        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("status", HttpStatus.SERVICE_UNAVAILABLE);
        errorMap.put("message", "");
        return errorMap;
    }
}
