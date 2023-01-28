package com.muravev.monitoringservice.models;

import com.muravev.monitoringservice.enums.TransportType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AvailableTransport {

    private long transportId;

    private double lat;

    private double lng;

    private LocalDateTime timestamp;
}
