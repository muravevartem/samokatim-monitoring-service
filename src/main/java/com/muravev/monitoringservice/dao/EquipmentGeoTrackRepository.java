package com.muravev.monitoringservice.dao;

import com.muravev.monitoringservice.entity.GeoPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.List;

public interface EquipmentGeoTrackRepository extends JpaRepository<GeoPoint, Long> {


    @Query("""
            SELECT point FROM GeoPoint point
            WHERE point.equipment.id = :equipmentId
                    AND point.createdDate BETWEEN :start AND :end
            ORDER BY point.createdDate ASC
            """)
    List<GeoPoint> findAllById(Long equipmentId,
                               ZonedDateTime start,
                               ZonedDateTime end);

}
