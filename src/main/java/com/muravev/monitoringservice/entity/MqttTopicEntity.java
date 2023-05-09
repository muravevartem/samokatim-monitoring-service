package com.muravev.monitoringservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

import java.util.Objects;

@Entity
@Table(name = "mqtt_topic")
@Getter
@Setter
@Accessors(chain = true)
public class MqttTopicEntity implements Persistable<String> {
    @Id
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private long organizationId;

    @Transient
    private boolean isNew;


    @Override
    public String getId() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MqttTopicEntity that = (MqttTopicEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
