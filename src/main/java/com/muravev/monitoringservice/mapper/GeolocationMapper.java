package com.muravev.monitoringservice.mapper;

import com.muravev.monitoringservice.entity.GeoEquipment;
import com.muravev.monitoringservice.entity.GeoPoint;
import com.muravev.monitoringservice.model.request.EquipmentGeolocationRequest;
import com.muravev.monitoringservice.model.response.EquipmentPointResponse;
import com.muravev.monitoringservice.model.response.EquipmentTrackResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface GeolocationMapper {
    GeoEquipment toCurrentLocation(EquipmentGeolocationRequest request);

    GeoPoint toTrackPoint(EquipmentGeolocationRequest request);

    EquipmentPointResponse toGeolocationResponse(GeoEquipment document);

    EquipmentTrackResponse toGeolocationResponse(GeoPoint document);

    @Mapping(target = "id", ignore = true)
    GeoEquipment merge(@MappingTarget GeoEquipment target, GeoEquipment source);
}
