package com.muravev.monitoringservice.service;

import com.muravev.monitoringservice.model.response.EquipmentTrackResponse;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public interface EquipmentTracker {
    List<EquipmentTrackResponse> getByEquipment(long equipmentId, OffsetDateTime start, OffsetDateTime end);
}
