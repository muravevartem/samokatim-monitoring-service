package com.muravev.monitoringservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "current_geo_point")
@Getter
@Setter
public class GeoEquipment implements Persistable<Long> {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EquipmentStatus status;

    @Column(nullable = false)
    private double lat;

    @Column(nullable = false)
    private double lng;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    @OrderBy("createdDate DESC")
    private List<GeoPoint> points = new ArrayList<>();

    @Transient
    private boolean isNew;

    public void addPoint(GeoPoint point) {
        if (point != null) {
            this.points.add(point);
            point.setEquipment(this);
        }
    }

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
