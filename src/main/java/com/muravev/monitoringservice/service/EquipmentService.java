package com.muravev.monitoringservice.service;

import com.muravev.monitoringservice.entity.EquipmentStatus;

public interface EquipmentService {
    void importNewEquipment(long equipmentId);

    void changeStatus(long equipmentId, EquipmentStatus status);
}
