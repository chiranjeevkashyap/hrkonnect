package com.chiranjeevkashyap.hrkonnect.advices;

import com.chiranjeevkashyap.hrkonnect.exceptions.ResourceNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseBody<?>> handleInternalServerError(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        ResponseError error = ResponseError.builder().status(HttpStatus.BAD_REQUEST).error("Input validation failed.").errors(errors).build();
        return buildResponseErrorEntity(error);
    }

    private ResponseEntity<ResponseBody<?>> buildResponseErrorEntity(ResponseError error) {
        return new ResponseEntity<>(new ResponseBody<>(error), error.getStatus());
    }
}
