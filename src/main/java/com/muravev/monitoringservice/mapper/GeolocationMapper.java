package com.muravev.monitoringservice.mapper;

import com.muravev.monitoringservice.entity.GeoEquipment;
import com.muravev.monitoringservice.entity.GeoPoint;
import com.muravev.monitoringservice.model.request.EquipmentGeolocationRequest;
import com.muravev.monitoringservice.model.response.EquipmentPointResponse;
import com.muravev.monitoringservice.model.response.EquipmentTrackResponse;
import org.mapstruct.Mapper;

@Mapper
public interface GeolocationMapper {

    GeoPoint toTrackPoint(EquipmentGeolocationRequest request);

    EquipmentPointResponse toGeolocationResponse(GeoEquipment document);

    EquipmentTrackResponse toGeolocationResponse(GeoPoint document);
}
