package com.muravev.monitoringservice.mqtt.config;

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

import java.util.List;
import java.util.UUID;

@Configuration
@Slf4j
public class MqttConfiguration {
    @Value("${mqtt.broker-url}")
    private String brokerUrl;

    @Value("${mqtt.topics}")
    private List<String> topics;

    @Bean
    IntegrationFlow mqttInbound() {
        return IntegrationFlow.from(
                new MqttPahoMessageDrivenChannelAdapter(brokerUrl, UUID.randomUUID().toString(), "smkt/geo")
        )
                .handle(m -> log.info("[MQTT] Message {}", m.getPayload()))
                .get();
    }
}
