package com.rms.rms.exception;

import com.rms.rms.dto.exception.ExceptionResponse;
import com.rms.rms.dto.exception.Message;
import org.hibernate.JDBCException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( value = {JDBCException.class })
    public ResponseEntity<ExceptionResponse> handlePersistenceException(JDBCException persistenceException) {
        List<String> tokens = Arrays.asList(String.valueOf(persistenceException.getSQLException()).split("ERROR:|Detail:"));
        tokens = tokens.stream().map(token -> {
                    token = token.trim();
                    token = token.substring(0,1).toUpperCase() + token.substring(1).toLowerCase();
                    return token;
                }
        ).collect(Collectors.toList());
        Message message = new Message(tokens.get(1), tokens.get(2));
        return createResponse(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    public ResponseEntity<ExceptionResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException emptyResultDataAccessException) {
        Message message = new Message("Internal server error!", emptyResultDataAccessException.getMessage());
        return createResponse(message, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ExceptionResponse> createResponse(Message message, HttpStatus httpStatus) {
        return new ResponseEntity<>(
                new ExceptionResponse(httpStatus, Collections.singletonList(message), LocalDateTime.now()),
                httpStatus
        );
    }


}
