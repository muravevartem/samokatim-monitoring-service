package com.muravev.monitoringservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AvailableTransport {

    private long transportId;

    private double lat;

    private double lng;

    private LocalDateTime timestamp;
}
