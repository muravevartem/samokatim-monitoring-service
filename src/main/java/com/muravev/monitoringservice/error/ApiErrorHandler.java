package com.muravev.monitoringservice.error;

import com.muravev.monitoringservice.error.data.BadField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApiErrorHandler {

    @ExceptionHandler
    public ResponseEntity<ApiError> handle(ApiException e) {
        log.error("Api error", e);
        return ResponseEntity.status(e.getStatus().getHttpStatus())
                .body(ApiError.builder()
                        .code(e.getStatus())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handle(MethodArgumentNotValidException e) {
        var allErrors = e.getBindingResult().getFieldErrors().stream()
                .map(error ->
                        new BadField(
                                error.getField(),
                                "Поле '%s' %s".formatted(error.getField(), error.getDefaultMessage())
                        )
                )
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiError.builder()
                        .code(StatusCode.VALIDATION_ERROR)
                        .message("Не валидный запрос")
                        .data(allErrors)
                        .build());
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handle(Exception e) {
        log.error("Internal server error", e);
        return ResponseEntity.status(StatusCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(ApiError.builder()
                        .code(StatusCode.INTERNAL_SERVER_ERROR)
                        .message(StatusCode.INTERNAL_SERVER_ERROR.getMessage())
                        .build());
    }
}
