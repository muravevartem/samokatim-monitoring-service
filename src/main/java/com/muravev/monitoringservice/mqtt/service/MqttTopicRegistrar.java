package com.muravev.monitoringservice.mqtt.service;

import com.muravev.monitoringservice.model.response.MqttTopicResponse;

import java.util.List;

public interface MqttTopicRegistrar {
    void registerTopic(String topicName);

    List<MqttTopicResponse> getTopics();
}
