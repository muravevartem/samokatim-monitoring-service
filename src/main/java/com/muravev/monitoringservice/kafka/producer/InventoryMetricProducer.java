package com.muravev.monitoringservice.kafka.producer;

import com.muravev.monitoringservice.kafka.TopicName;
import com.muravev.samokatimmessage.GeoPointReceivedMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryMetricProducer {
    private final KafkaTemplate<String, GeoPointReceivedMessage> kafkaTemplate;


    public void sendMetric(GeoPointReceivedMessage message) {
        kafkaTemplate.send(
                TopicName.GEO_POINT_RECEIVED,
                message.getInventoryId().toString(),
                message
        );
    }
}
