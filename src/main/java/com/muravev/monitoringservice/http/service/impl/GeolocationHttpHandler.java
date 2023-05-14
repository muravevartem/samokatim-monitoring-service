package com.muravev.monitoringservice.http.service.impl;

import com.muravev.monitoringservice.http.service.HttpHandler;
import com.muravev.monitoringservice.kafka.producer.InventoryMetricProducer;
import com.muravev.monitoringservice.model.in.MonitoringRecordIn;
import com.muravev.samokatimmessage.GeoPointReceivedMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeolocationHttpHandler implements HttpHandler {
    private final InventoryMetricProducer metricProducer;


    @Override
    public void handle(MonitoringRecordIn record) {
        GeoPointReceivedMessage kafkaMessage = GeoPointReceivedMessage.newBuilder()
                .setInventoryId(record.clientId())
                .setSatellites(record.satellites())
                .setLat(record.lat())
                .setLng(record.lng())
                .setSpeed(record.speed())
                .setAltitude(record.altitude())
                .setTimestamp(ZonedDateTime.now().toInstant().getEpochSecond())
                .build();
        metricProducer.sendMetric(kafkaMessage);
    }
}
