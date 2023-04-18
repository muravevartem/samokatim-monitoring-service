package com.muravev.monitoringservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "current_geo_point")
@Getter
@Setter
public class GeoEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "current_geo_point_gen")
    @SequenceGenerator(name = "current_geo_point_gen", sequenceName = "current_geo_point_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private long equipmentId;

    @Column(nullable = false)
    private double lat;

    @Column(nullable = false)
    private double lng;

    @OneToMany(mappedBy = "equipment")
    @OrderBy("createdDate DESC")
    private List<GeoPoint> points = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GeoEquipment that = (GeoEquipment) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
