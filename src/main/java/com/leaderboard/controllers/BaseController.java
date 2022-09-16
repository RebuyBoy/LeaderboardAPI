package com.leaderboard.controllers;

import com.leaderboard.exceptions.AesException;
import com.leaderboard.exceptions.NoResultException;
import com.leaderboard.exceptions.ProviderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

//TODO @RequestMapping("leaderboard")
public interface BaseController {

    @ExceptionHandler(value = AesException.class)
    default ResponseEntity<Object> exception(AesException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(value = NoResultException.class)
    default ResponseEntity<Object> exception(NoResultException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NullPointerException.class)
    default ResponseEntity<Object> exception(NullPointerException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>("Oooops something wrong. We are working!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ProviderNotFoundException.class)
    default ResponseEntity<Object> exception(ProviderNotFoundException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FAILED_DEPENDENCY);
    }

}
