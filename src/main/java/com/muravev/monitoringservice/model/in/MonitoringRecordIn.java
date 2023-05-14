package com.muravev.monitoringservice.model.in;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MonitoringRecordIn(
        @NotNull
        Long clientId,
        @NotNull
        Double lat,
        @NotNull
        Double lng,
        @Positive
        double speed,
        @NotNull
        Double altitude,
        @Positive
        int satellites
) {
}
