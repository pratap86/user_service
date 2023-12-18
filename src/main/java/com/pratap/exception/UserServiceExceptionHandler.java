package com.pratap.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class UserServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String errorMessage = fieldErrors.stream()
                .map(fieldError -> fieldError.getField() + " - " + fieldError.getDefaultMessage())
                .sorted().collect(Collectors.joining(","));
        UserServiceExceptionResponse userServiceExceptionResponse = new UserServiceExceptionResponse(new Date(),
                errorMessage, ex.getBindingResult().toString());
        return new ResponseEntity<>(userServiceExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public final ResponseEntity<UserServiceExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
        UserServiceExceptionResponse userServiceExceptionResponse = new UserServiceExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(userServiceExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<UserServiceExceptionResponse> handleNotFoundException(UserNotFoundException ex, WebRequest request) {
        UserServiceExceptionResponse userServiceExceptionResponse = new UserServiceExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(userServiceExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<UserServiceExceptionResponse> handleOtherException(Exception ex, WebRequest request) {
        UserServiceExceptionResponse userServiceExceptionResponse = new UserServiceExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(userServiceExceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
