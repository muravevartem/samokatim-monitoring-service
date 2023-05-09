package com.muravev.monitoringservice.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StatusCode {

    FORBIDDEN(HttpStatus.FORBIDDEN, "Нет доступа"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Внутренняя ошибка сервера"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Ошибка валидации"),

    TOPIC_NOT_FOUND(HttpStatus.NOT_FOUND, "Топик не найден"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
