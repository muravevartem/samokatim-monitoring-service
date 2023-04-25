package com.muravev.monitoringservice.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.muravev.monitoringservice.util.JsonTimeFormat;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class EquipmentTrackResponse {
    private Long id;
    private Double lat;
    private Double lng;

    @JsonFormat(pattern = JsonTimeFormat.ISO_DATE_TIME)
    private ZonedDateTime createdDate;
}
