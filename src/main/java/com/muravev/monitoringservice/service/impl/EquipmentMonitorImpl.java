package com.muravev.monitoringservice.service.impl;

import com.muravev.monitoringservice.dao.GeoEquipmentRepository;
import com.muravev.monitoringservice.dao.EquipmentGeoTrackRepository;
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
    private final EquipmentGeoTrackRepository trackRepository;
    private final GeolocationMapper geolocationMapper;


    @Override
    @Transactional
    public EquipmentPointResponse saveGeolocation(EquipmentGeolocationRequest geolocationRequest) {
        GeoEquipment source = geolocationMapper.toCurrentLocation(geolocationRequest);
        GeoEquipment currentGeolocationForSave = equipmentRepository.findByEquipmentId(source.getEquipmentId())
                .map(target -> geolocationMapper.merge(target, source))
                .orElse(source);
        GeoPoint point = geolocationMapper.toTrackPoint(geolocationRequest);
        currentGeolocationForSave.addPoint(point);
        var savedGeolocation = equipmentRepository.save(currentGeolocationForSave);
        return geolocationMapper.toGeolocationResponse(savedGeolocation);
    }

}
