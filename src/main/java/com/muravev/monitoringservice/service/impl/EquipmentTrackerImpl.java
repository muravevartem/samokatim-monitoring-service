package com.muravev.monitoringservice.service.impl;

import com.muravev.monitoringservice.dao.EquipmentGeoTrackRepository;
import com.muravev.monitoringservice.mapper.GeolocationMapper;
import com.muravev.monitoringservice.model.response.EquipmentTrackResponse;
import com.muravev.monitoringservice.service.EquipmentTracker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EquipmentTrackerImpl implements EquipmentTracker {
    private final EquipmentGeoTrackRepository trackRepository;
    private final GeolocationMapper geolocationMapper;

    @Override
    @Transactional(readOnly = true)
    public List<EquipmentTrackResponse> getByEquipment(long equipmentId, ZonedDateTime start, ZonedDateTime end) {
        var track = trackRepository.findAllById(equipmentId,
                start,
                end
        );
        return track.stream()
                .map(geolocationMapper::toGeolocationResponse)
                .collect(Collectors.toList());
    }
}
