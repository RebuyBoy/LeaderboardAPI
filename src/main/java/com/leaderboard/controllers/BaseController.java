package com.leaderboard.controllers;

import com.leaderboard.exceptions.AesException;
import com.leaderboard.exceptions.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseController {

    @ExceptionHandler(value = AesException.class)
    public ResponseEntity<Object> exception(AesException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(value = NoResultException.class)
    public ResponseEntity<Object> exception(NoResultException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<Object> exception() {
        return new ResponseEntity<>("Oooops something wrong. We are working!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
