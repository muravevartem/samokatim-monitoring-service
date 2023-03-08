package com.muravev.monitoringservice.mqtt.service;

import org.springframework.messaging.Message;

public interface MqttHandler {
    void handle(Message<?> message);
}
