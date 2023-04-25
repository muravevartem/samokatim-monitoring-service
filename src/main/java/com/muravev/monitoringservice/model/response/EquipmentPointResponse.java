package com.muravev.monitoringservice.model.response;

import lombok.Data;

@Data
public class EquipmentPointResponse {
    private Long equipmentId;
    private String status;
    private Double lat;
    private Double lng;
}
