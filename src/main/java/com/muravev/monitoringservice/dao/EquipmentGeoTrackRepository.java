package com.muravev.monitoringservice.dao;

import com.muravev.monitoringservice.entity.GeoPoint;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public interface EquipmentGeoTrackRepository extends JpaRepository<GeoPoint, Long> {


    @Query("""
            SELECT point FROM GeoPoint point
            WHERE point.equipment.id = :equipmentId
                    AND point.createdDate BETWEEN :start AND :end
            """)
    List<GeoPoint> findAllByTransportId(Long equipmentId,
                                        OffsetDateTime start,
                                        OffsetDateTime end,
                                        Sort sort);

}
