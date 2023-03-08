package com.muravev.monitoringservice.mqtt.service.impl;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public enum MqttMessageType {
    GEO;

    public static Optional<MqttMessageType> fromString(String strType) {
        return Arrays.stream(MqttMessageType.values())
                .filter(type -> type.name().equals(strType))
                .findFirst();
    }
}
