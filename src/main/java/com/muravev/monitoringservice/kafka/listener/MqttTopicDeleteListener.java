package com.muravev.monitoringservice.kafka.listener;

import com.muravev.monitoringservice.kafka.TopicName;
import com.muravev.monitoringservice.mqtt.service.MqttTopicRegistrar;
import com.muravev.samokatimmessage.MqttTopicAddMessage;
import com.muravev.samokatimmessage.MqttTopicDeleteMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MqttTopicDeleteListener {
    private final MqttTopicRegistrar topicRegistrar;


    @KafkaListener(topics = TopicName.MQTT_TOPIC_DELETE)
    public void listen(MqttTopicDeleteMessage message) {
        topicRegistrar.unregisterTopic(message.getOrganizationId(), message.getTopicName());
    }
}
