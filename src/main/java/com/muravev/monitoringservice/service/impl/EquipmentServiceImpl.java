package com.muravev.monitoringservice.service.impl;

import com.muravev.monitoringservice.dao.GeoEquipmentRepository;
import com.muravev.monitoringservice.entity.EquipmentStatus;
import com.muravev.monitoringservice.entity.GeoEquipment;
import com.muravev.monitoringservice.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EquipmentServiceImpl implements EquipmentService {
    private final GeoEquipmentRepository equipmentRepository;


    @Override
    public void importNewEquipment(long equipmentId) {
        GeoEquipment geoEquipment = new GeoEquipment();
        geoEquipment.setId(equipmentId);
        geoEquipment.setNew(true);
        geoEquipment.setStatus(EquipmentStatus.WAITING);
        equipmentRepository.save(geoEquipment);
    }

    @Override
    @Transactional
    public void changeStatus(long equipmentId, EquipmentStatus status) {
        equipmentRepository.findById(equipmentId)
                .ifPresent(equipment -> equipment.setStatus(status));
    }
}
