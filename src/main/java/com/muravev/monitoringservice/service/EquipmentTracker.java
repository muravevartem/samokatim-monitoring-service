package com.muravev.monitoringservice.service;

import com.muravev.monitoringservice.model.response.EquipmentTrackResponse;

import java.time.ZonedDateTime;
import java.util.List;

public interface EquipmentTracker {
    List<EquipmentTrackResponse> getByEquipment(long equipmentId, ZonedDateTime start, ZonedDateTime end);
}
