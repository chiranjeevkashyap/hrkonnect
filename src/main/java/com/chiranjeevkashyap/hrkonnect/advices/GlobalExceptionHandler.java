package com.chiranjeevkashyap.hrkonnect.advices;

import com.chiranjeevkashyap.hrkonnect.exceptions.BusinessRuleViolationException;
import com.chiranjeevkashyap.hrkonnect.exceptions.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseBody<?>> handleResourceNotFound(ResourceNotFoundException exception) {
        ResponseError error = ResponseError.builder().status(HttpStatus.NOT_FOUND).error(exception.getMessage()).build();
        return buildResponseErrorEntity(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBody<?>> handleInternalServerError(Exception exception) {
        ResponseError error = ResponseError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).error(exception.getMessage()).build();
        return buildResponseErrorEntity(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBody<?>> handleMethodArgumentNotValidError(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        ResponseError error = ResponseError.builder().status(HttpStatus.BAD_REQUEST).error("Input validation failed.").errors(errors).build();
        return buildResponseErrorEntity(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseBody<?>> handleHttpMessageNotReadableError(HttpMessageNotReadableException exception) {
        ResponseError error = ResponseError.builder().status(HttpStatus.BAD_REQUEST).error("Request body is required.").build();
        return buildResponseErrorEntity(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseBody<?>> handleConstraintViolationError(ConstraintViolationException exception) {
        List<String> errors = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .toList();
        ResponseError error = ResponseError.builder().status(HttpStatus.BAD_REQUEST).error("Validation Failed.").errors(errors).build();
        return buildResponseErrorEntity(error);
    }

    @ExceptionHandler(BusinessRuleViolationException.class)
    public ResponseEntity<ResponseBody<?>> handleBusinessException(BusinessRuleViolationException exception) {
        ResponseError error = ResponseError.builder().status(HttpStatus.CONFLICT).error("Business Rule Violation.").errors(List.of(exception.getMessage())).build();
        return buildResponseErrorEntity(error);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseBody<?>> handleInternalServerError(UsernameNotFoundException exception) {
        ResponseError error = ResponseError.builder().status(HttpStatus.NOT_FOUND).error("Username not found with username: ").build();
        return buildResponseErrorEntity(error);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ResponseBody<?>> handleInternalServerError(JwtException exception) {
        ResponseError error = ResponseError.builder().status(HttpStatus.NOT_FOUND).error("Invalid JWT token: ").build();
        return buildResponseErrorEntity(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseBody<?>> handleInternalServerError(AccessDeniedException exception) {
        ResponseError error = ResponseError.builder().status(HttpStatus.NOT_FOUND).error("Access denied: Insufficient permissions").build();
        return buildResponseErrorEntity(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseBody<?>> handleInternalServerError(AuthenticationException exception) {
        ResponseError error = ResponseError.builder().status(HttpStatus.NOT_FOUND).error("Authentication failed: ").build();
        return buildResponseErrorEntity(error);
    }

    private ResponseEntity<ResponseBody<?>> buildResponseErrorEntity(ResponseError error) {
        return new ResponseEntity<>(new ResponseBody<>(error), error.getStatus());
    }
}
