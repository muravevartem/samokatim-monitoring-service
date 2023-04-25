package com.muravev.monitoringservice.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentGeolocationRequest {
    @NotNull
    @Positive
    private Long id;
    @NotNull
    private Double lat;

    @NotNull
    private Double lng;
}
