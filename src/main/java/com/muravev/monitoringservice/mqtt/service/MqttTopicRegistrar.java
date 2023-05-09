package com.muravev.monitoringservice.mqtt.service;

import com.muravev.monitoringservice.entity.MqttTopicEntity;

import java.util.List;

public interface MqttTopicRegistrar {
    void registerTopic(long orgId, String topicName);

    void unregisterTopic(long orgId, String topicName);

    void unregisterTopic(String topicId);

    List<MqttTopicEntity> getTopics(long orgId);
}
