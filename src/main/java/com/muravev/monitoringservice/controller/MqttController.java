package com.muravev.monitoringservice.controller;

import com.muravev.monitoringservice.entity.MqttTopicEntity;
import com.muravev.monitoringservice.model.in.MqttTopicAddCommand;
import com.muravev.monitoringservice.model.out.MqttTopicOut;
import com.muravev.monitoringservice.mqtt.service.MqttTopicRegistrar;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/topics")
@RequiredArgsConstructor
public class MqttController {
    private final MqttTopicRegistrar topicRegistrar;

    @PostMapping
    public void register(@Valid @RequestBody MqttTopicAddCommand command) {
       topicRegistrar.registerTopic(command.orgId(), command.topicName());
    }

    @DeleteMapping("/{topicId}")
    public void delete(@PathVariable String topicId) {
        topicRegistrar.unregisterTopic(topicId);
    }

    @GetMapping(params = "orgId")
    public List<MqttTopicOut> getTopics(@RequestParam long orgId) {
        List<MqttTopicEntity> topics = topicRegistrar.getTopics(orgId);
        return topics.stream()
                .map(x->new MqttTopicOut(x.getOrganizationId(), x.getName()))
                .toList();
    }
}
