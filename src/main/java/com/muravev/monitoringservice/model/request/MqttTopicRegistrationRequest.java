package com.muravev.monitoringservice.model.request;

import lombok.Data;

@Data
public class MqttTopicRegistrationRequest {
    private long companyId;
    private String topicName;
}
