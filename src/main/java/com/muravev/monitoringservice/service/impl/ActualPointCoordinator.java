package com.muravev.monitoringservice.service.impl;

import com.muravev.monitoringservice.dao.GeoEquipmentRepository;
import com.muravev.monitoringservice.entity.GeoEquipment;
import com.muravev.monitoringservice.error.ApiException;
import com.muravev.monitoringservice.error.StatusCode;
import com.muravev.monitoringservice.mapper.GeolocationMapper;
import com.muravev.monitoringservice.model.request.MapViewRequest;
import com.muravev.monitoringservice.model.response.EquipmentPointResponse;
import com.muravev.monitoringservice.service.PointCoordinator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActualPointCoordinator implements PointCoordinator {
    private final GeoEquipmentRepository equipmentRepository;
    private final GeolocationMapper geolocationMapper;


    @Override
    @Transactional(readOnly = true)
    public List<EquipmentPointResponse> getActualGeolocations(MapViewRequest viewRequest) {

        var northEast = viewRequest.getNorthEast();
        var southWest = viewRequest.getSouthWest();
        log.info("[GEO] getting location ne-lat: {} ne-lng: {} sw-lat: {} sw-lng: {}",
                northEast.getLat(), northEast.getLng(), southWest.getLat(), southWest.getLng());
        var equipments = equipmentRepository.findAllBySearchPerimeter(
                northEast.getLat(),
                northEast.getLng(),
                southWest.getLat(),
                southWest.getLng()
        );
        return equipments.stream()
                .map(geolocationMapper::toGeolocationResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EquipmentPointResponse getActualGeolocation(long id) {
        GeoEquipment geoEquipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new ApiException(StatusCode.EQUIPMENT_NOT_FOUND));
        return geolocationMapper.toGeolocationResponse(geoEquipment);
    }
}
