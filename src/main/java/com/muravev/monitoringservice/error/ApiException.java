package com.muravev.monitoringservice.error;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final StatusCode status;

    public ApiException(StatusCode code) {
        super(code.getMessage());
        this.status = code;
    }
}
