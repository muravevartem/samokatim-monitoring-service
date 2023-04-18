package com.muravev.monitoringservice.service.impl;

import com.muravev.monitoringservice.dao.GeoEquipmentRepository;
import com.muravev.monitoringservice.mapper.GeolocationMapper;
import com.muravev.monitoringservice.model.request.MapViewRequest;
import com.muravev.monitoringservice.model.response.EquipmentPointResponse;
import com.muravev.monitoringservice.service.PointCoordinator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActualPointCoordinator implements PointCoordinator {
    private final GeoEquipmentRepository equipmentRepository;
    private final GeolocationMapper geolocationMapper;


    @Override
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
}
