package com.muravev.monitoringservice.error.data;

import lombok.Data;

@Data
public class BadField {
    private final String fieldName;
    private final String message;
}
