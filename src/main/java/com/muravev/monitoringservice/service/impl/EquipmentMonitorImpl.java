package com.muravev.monitoringservice.service.impl;

import com.muravev.monitoringservice.dao.GeoEquipmentRepository;
import com.muravev.monitoringservice.entity.GeoEquipment;
import com.muravev.monitoringservice.entity.GeoPoint;
import com.muravev.monitoringservice.mapper.GeolocationMapper;
import com.muravev.monitoringservice.model.request.EquipmentGeolocationRequest;
import com.muravev.monitoringservice.model.response.EquipmentPointResponse;
import com.muravev.monitoringservice.service.EquipmentMonitor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class EquipmentMonitorImpl implements EquipmentMonitor {
    private final GeoEquipmentRepository equipmentRepository;
    private final GeolocationMapper geolocationMapper;


    @Override
    @Transactional
    public EquipmentPointResponse saveGeolocation(EquipmentGeolocationRequest geolocationRequest) {
        GeoEquipment equipment = equipmentRepository.findById(geolocationRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException(""));
        equipment.setLat(geolocationRequest.getLat());
        equipment.setLng(geolocationRequest.getLng());
        GeoPoint point = geolocationMapper.toTrackPoint(geolocationRequest);
        equipment.addPoint(point);
        log.info("New point lat:{} lng:{}", point.getLat(), point.getLng());
        var savedGeolocation = equipmentRepository.save(equipment);
        return geolocationMapper.toGeolocationResponse(savedGeolocation);
    }

}
