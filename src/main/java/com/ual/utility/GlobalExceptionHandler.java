package com.ual.utility;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.ual.exception.URSException;

@RestControllerAdvice
public class GlobalExceptionHandler {
@Autowired
Environment env;
    @ExceptionHandler(URSException.class)
    public ResponseEntity<ErrorInfo> handleURSException(URSException ex, WebRequest request) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorInfo.setMessage(env.getProperty(ex.getMessage()));
        errorInfo.setPath(request.getDescription(false));
        errorInfo.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());

        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        errorInfo.setMessage(env.getProperty(ex.getMessage(), errorMessage));
        errorInfo.setPath(request.getDescription(false));
        errorInfo.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorInfo.setMessage(env.getProperty(ex.getMessage(), "An unexpected error occurred,Method Argument Not Valid"));
        errorInfo.setPath(request.getDescription(false));
        errorInfo.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
 