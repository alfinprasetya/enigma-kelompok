package com.enigma.kelompok.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.enigma.kelompok.utils.response.Res;

@RestControllerAdvice
public class ErrorController {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleConstraintViolation(MethodArgumentNotValidException e) {
    return Res.renderJson(null, e.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
