package com.rms.rms.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {

    private HttpStatus httpStatus;
    private List<Message> message;
    private LocalDateTime timestamp;

}
