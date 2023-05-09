package com.muravev.monitoringservice.model.out;

public record MqttTopicOut(
        long organziationId,
        String topicName
) {
}
