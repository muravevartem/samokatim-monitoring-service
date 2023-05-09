package com.muravev.monitoringservice.mqtt.service.impl;

import com.muravev.monitoringservice.entity.MqttTopicEntity;
import com.muravev.monitoringservice.error.ApiException;
import com.muravev.monitoringservice.error.StatusCode;
import com.muravev.monitoringservice.mqtt.service.MqttTopicRegistrar;
import com.muravev.monitoringservice.rep.MqttTopicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MqttTopicRegistrarImpl implements MqttTopicRegistrar {
    private final MqttTopicRepository mqttTopicRepository;
    private final MqttPahoMessageDrivenChannelAdapter mqttAdapter;


    @Override
    @Transactional
    public void registerTopic(long orgId, String topicName) {
        MqttTopicEntity mqttTopic = new MqttTopicEntity()
                .setName(topicName)
                .setOrganizationId(orgId);
        mqttTopicRepository.save(mqttTopic);
        mqttAdapter.addTopic(topicName);
    }

    @Override
    @Transactional
    public void unregisterTopic(long orgId, String topicName) {
        MqttTopicEntity mqttTopic = mqttTopicRepository.findById(topicName)
                .filter(t -> t.getOrganizationId() == orgId)
                .orElseThrow(() -> new ApiException(StatusCode.TOPIC_NOT_FOUND));
        mqttTopicRepository.delete(mqttTopic);
        mqttAdapter.removeTopic(mqttTopic.getName());
    }

    @Override
    @Transactional
    public void unregisterTopic(String topicId) {
        MqttTopicEntity mqttTopic = mqttTopicRepository.findById(topicId)
                .orElseThrow(() -> new ApiException(StatusCode.TOPIC_NOT_FOUND));
        mqttTopicRepository.delete(mqttTopic);
        mqttAdapter.removeTopic(mqttTopic.getName());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MqttTopicEntity> getTopics(long orgId) {
        return mqttTopicRepository.findAllByOrganizationId(orgId);
    }
}
