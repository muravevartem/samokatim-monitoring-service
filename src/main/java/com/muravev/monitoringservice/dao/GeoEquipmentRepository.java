package com.muravev.monitoringservice.dao;

import com.muravev.monitoringservice.entity.GeoEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GeoEquipmentRepository extends JpaRepository<GeoEquipment, Long> {

    @Query("""
            SELECT equipment FROM GeoEquipment equipment
            WHERE equipment.lng BETWEEN :lngSW AND :lngNE
                AND
                equipment.lat BETWEEN :latSW AND :latNE
            ORDER BY equipment.id DESC
            """)
    List<GeoEquipment> findAllBySearchPerimeter(@Param("latNE") double latNE,
                                            @Param("lngNE") double lngNE,
                                            @Param("latSW") double latSW,
                                            @Param("lngSW") double lngSW);

}
