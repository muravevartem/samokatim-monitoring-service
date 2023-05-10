package com.muravev.monitoringservice.mqtt.config;

import com.muravev.monitoringservice.entity.MqttTopicEntity;
import com.muravev.monitoringservice.mqtt.service.MqttHandler;
import com.muravev.monitoringservice.mqtt.service.MqttHandlingManager;
import com.muravev.monitoringservice.rep.MqttTopicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class MqttConfiguration {

    private final MqttTopicRepository topicRepository;


    @Value("${mqtt.broker-url}")
    private String brokerUrl;

    @Value("${mqtt.client-id}")
    private String clientId;


    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter inbound() {
        String[] topics = topicRepository.findAll().stream()
                .map(MqttTopicEntity::getName)
                .toArray(String[]::new);
        log.info("Topics: {}", Arrays.asList(topics));
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(brokerUrl, clientId, topics);
        adapter.setAutoStartup(true);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler(MqttHandlingManager handlingManager) {
        return handlingManager::handleMessage;
    }

}
