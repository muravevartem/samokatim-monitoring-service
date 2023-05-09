package com.muravev.monitoringservice.mqtt.service.impl;

import com.muravev.monitoringservice.kafka.producer.InventoryMetricProducer;
import com.muravev.monitoringservice.mqtt.service.MqttHandler;
import com.muravev.samokatimmessage.GeoPointReceivedMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeolocationMqttHandler implements MqttHandler {
    private final InventoryMetricProducer metricProducer;


    @Override
    public void handle(Message<?> message) {
        String geoMessage = String.valueOf(message.getPayload());

        String[] splitMessage = geoMessage.split(";");
        if (splitMessage.length != MessageStructure.values().length) {
            log.warn("[MQTT-GEO] Invalid message");
            return;
        }

        MqttMessageType messageType = MqttMessageType.fromString(splitMessage[MessageStructure.TYPE.ordinal()])
                .orElse(null);

        if (messageType != MqttMessageType.GEO) {
            log.warn("[MQTT-GEO] Invalid message type {}", splitMessage[0]);
            return;
        }

        Long clientId = getValueOrNull(splitMessage, MessageStructure.CLIENT_ID, Long::parseLong);
        assert clientId != null;

        Integer satellites = getValueOrNull(splitMessage, MessageStructure.SATELLITES, Integer::parseInt);
        Double lat = getValueOrNull(splitMessage, MessageStructure.LAT, Double::parseDouble);
        Double lng = getValueOrNull(splitMessage, MessageStructure.LNG, Double::parseDouble);
        Double speed = getValueOrNull(splitMessage, MessageStructure.SPEED, Double::parseDouble);
        Double altitude = getValueOrNull(splitMessage, MessageStructure.ALTITUDE, Double::parseDouble);

        log.info("Registered new geolocation: client-{} lat-{} lng-{} satellise-{} speed-{}",
                clientId, lat, lng, satellites, speed);

        GeoPointReceivedMessage kafkaMessage = GeoPointReceivedMessage.newBuilder()
                .setInventoryId(clientId)
                .setSatellites(satellites != null ? satellites : 0)
                .setLat(lat)
                .setLng(lng)
                .setSpeed(speed)
                .setAltitude(altitude)
                .setTimestamp(ZonedDateTime.now().toInstant().getEpochSecond())
                .build();

        metricProducer.sendMetric(kafkaMessage);
    }

    private <T> T getValueOrNull(String[] message, MessageStructure structure, Function<String, T> mapper) {
        if (structure.ordinal() >= message.length)
            return null;

        String item = message[structure.ordinal()];
        if (StringUtils.isEmpty(item))
            return null;

        return mapper.apply(item);
    }
}
