package com.muravev.monitoringservice.mqtt.service;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;

public interface MqttHandlingManager {
    void handleMessage(Message<?> message);
}
