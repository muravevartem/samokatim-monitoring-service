package com.muravev.monitoringservice.kafka;

import com.muravev.monitoringservice.entity.EquipmentStatus;
import com.muravev.monitoringservice.service.EquipmentService;
import com.muravev.samokatimmessage.BookedEquipmentMessage;
import com.muravev.samokatimmessage.RegisteredEquipmentMessage;
import com.muravev.samokatimmessage.ReleasedEquipmentMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = TopicName.EQUIPMENT_TOPIC)
@RequiredArgsConstructor
@Slf4j
public class EquipmentListener {
    private final EquipmentService equipmentService;


    @KafkaHandler
    public void listen(RegisteredEquipmentMessage message) {
        equipmentService.importNewEquipment(message.getId());
    }

    @KafkaHandler
    public void listen(BookedEquipmentMessage message) {
        equipmentService.changeStatus(message.getId(), EquipmentStatus.USED);
    }

    @KafkaHandler
    public void listen(ReleasedEquipmentMessage message) {
        equipmentService.changeStatus(message.getId(), EquipmentStatus.WAITING);
    }

    @KafkaHandler(isDefault = true)
    public void listen(Object object) {
        log.warn("Unknown message type");
    }
}
