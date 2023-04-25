package com.muravev.monitoringservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "geo_point")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class GeoPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "geo_point_gen")
    @SequenceGenerator(name = "geo_point_gen", sequenceName = "geo_point_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private double lat;

    @Column(nullable = false)
    private double lng;

    @CreatedDate
    private ZonedDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipment_id", nullable = false)
    private GeoEquipment equipment;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GeoPoint geoPoint = (GeoPoint) o;
        return getId() != null && Objects.equals(getId(), geoPoint.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
