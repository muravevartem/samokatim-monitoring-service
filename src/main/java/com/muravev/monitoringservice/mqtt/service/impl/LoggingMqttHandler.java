package com.muravev.monitoringservice.mqtt.service.impl;

import com.muravev.monitoringservice.mqtt.service.MqttHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoggingMqttHandler implements MqttHandler {
    @Override
    public void handle(Message<?> message) {
        log.info("[MQTT] Message: {}", message.getPayload());
    }
}
