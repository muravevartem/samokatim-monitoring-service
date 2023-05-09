package com.muravev.monitoringservice.model.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MqttTopicAddCommand(
        @NotNull Long orgId,
        @NotBlank String topicName
) {
}
