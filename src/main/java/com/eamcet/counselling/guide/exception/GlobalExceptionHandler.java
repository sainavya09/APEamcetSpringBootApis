package com.eamcet.counselling.guide.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Date;

import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ExceptionResponse handleValidationExceptions(ConstraintViolationException exception,
                                                      WebRequest request) {
    String errorMessage = exception.getConstraintViolations().stream()
        .map(ConstraintViolation::getMessage)
        .collect(Collectors.joining("; "));
    log.error("ConstraintViolationException Exception occurred with issue {} ", errorMessage);
    return new ExceptionResponse(new Date().toString(), HttpStatus.BAD_REQUEST.toString(),
        errorMessage,
        request.getContextPath());
  }

  @ExceptionHandler(ApplicationException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ExceptionResponse handleApplicationExceptions(ApplicationException exception,
                                                       WebRequest request) {
    log.error("Application Exception occurred with issue {} ", exception.toString());
    return new ExceptionResponse(new Date().toString(), HttpStatus.BAD_REQUEST.toString(),
        exception.getMessage(),
        request.getContextPath());
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ExceptionResponse handleRuntimeExceptions(RuntimeException exception, WebRequest request) {
    log.error("Runtime Exception occurred with issue {}", exception.toString());
    return new ExceptionResponse(new Date().toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),
        exception.toString(), request.getContextPath());
  }
}
