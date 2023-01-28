package com.muravev.monitoringservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.muravev.monitoringservice.enums.TransportType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransportPoint {

    @NotNull
    @Min(0)
    @JsonProperty("tId")
    private Long transportId;

    @NotNull
    private Double lat;

    @NotNull
    private Double lng;

}
