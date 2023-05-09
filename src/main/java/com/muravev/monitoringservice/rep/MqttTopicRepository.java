package com.muravev.monitoringservice.rep;

import com.muravev.monitoringservice.entity.MqttTopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MqttTopicRepository extends JpaRepository<MqttTopicEntity, String> {
    List<MqttTopicEntity> findAllByOrganizationId(long organizationId);
}
