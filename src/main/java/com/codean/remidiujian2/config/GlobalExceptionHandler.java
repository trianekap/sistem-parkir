package com.codean.remidiujian2.config;

import com.codean.remidiujian2.exceptions.ResourceAlreadyExistException;
import com.codean.remidiujian2.exceptions.ResourceNotExistException;
import com.codean.remidiujian2.exceptions.ValueTooLongException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<List<String>> HandleMethodArgumentNotValidExceptionError(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(fe -> fe.getDefaultMessage()).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ResourceNotExistException.class)
    ResponseEntity<String> handleResourceNotExistExceptionError(ResourceNotExistException ex){
        String errorMsg = ex.getMessage();

        logger.error(errorMsg);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    ResponseEntity<String> handleResourceAlreadyExistExceptionError(ResourceAlreadyExistException ex){
        String errorMsg = ex.getMessage();

        logger.error(errorMsg);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMsg);
    }

    @ExceptionHandler(ValueTooLongException.class)
    public ResponseEntity<String> handleValueTooLongException(ValueTooLongException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<String> handleResourceNotExistExceptionError(IllegalArgumentException ex){
        String errorMsg = ex.getMessage();

        logger.error(errorMsg);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
    }

    @ExceptionHandler(FileNotFoundException.class)
    ResponseEntity<String> handleResourceNotExistExceptionError(FileNotFoundException ex){
        String errorMsg = ex.getMessage();

        logger.error(errorMsg);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
    }


}
