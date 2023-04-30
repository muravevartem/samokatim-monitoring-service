package com.muravev.monitoringservice.controller;

import com.muravev.monitoringservice.model.request.MqttTopicRegistrationRequest;
import com.muravev.monitoringservice.model.response.MqttTopicResponse;
import com.muravev.monitoringservice.mqtt.service.MqttTopicRegistrar;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/integrations/mqtt/topics")
@RequiredArgsConstructor
public class MqttController {
    private final MqttTopicRegistrar topicRegistrar;

    @PostMapping
    public void register(@RequestBody @Valid MqttTopicRegistrationRequest request) {
        topicRegistrar.registerTopic(request.getTopicName());
    }

    @GetMapping
    public Collection<MqttTopicResponse> getAllMyTopics() {
        return topicRegistrar.getTopics();
    }
}
