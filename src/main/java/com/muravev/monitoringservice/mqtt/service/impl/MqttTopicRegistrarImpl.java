package com.muravev.monitoringservice.mqtt.service.impl;

import com.muravev.monitoringservice.model.response.MqttTopicResponse;
import com.muravev.monitoringservice.mqtt.service.MqttTopicRegistrar;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MqttTopicRegistrarImpl implements MqttTopicRegistrar {
    private final MqttPahoMessageDrivenChannelAdapter mqttAdapter;


    @Override
    public void registerTopic(String topicName) {
        mqttAdapter.addTopic(topicName);
    }

    @Override
    public List<MqttTopicResponse> getTopics() {
        return Arrays.stream(mqttAdapter.getTopic())
                .map(topicName -> MqttTopicResponse.builder()
                        .topicName(topicName)
                        .build())
                .toList();
    }
}
