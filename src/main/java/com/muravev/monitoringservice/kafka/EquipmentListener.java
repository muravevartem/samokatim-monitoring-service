package com.muravev.monitoringservice.kafka;

import com.muravev.monitoringservice.entity.EquipmentStatus;
import com.muravev.monitoringservice.service.EquipmentService;
import com.muravev.samokatimmessage.BookedEquipmentMessage;
import com.muravev.samokatimmessage.RegisteredEquipmentMessage;
import com.muravev.samokatimmessage.ReleasedEquipmentMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EquipmentListener {
    private final EquipmentService equipmentService;


    @KafkaListener(topics = TopicName.REGISTERED_EQUIPMENT_TOPIC)
    public void listen(RegisteredEquipmentMessage message) {
        equipmentService.importNewEquipment(message.getId());
    }

    @KafkaListener(topics = TopicName.BOOKED_EQUIPMENT_TOPIC)
    public void listen(BookedEquipmentMessage message) {
        equipmentService.changeStatus(message.getId(), EquipmentStatus.USED);
    }

    @KafkaListener(topics = TopicName.RELEASED_EQUIPMENT_TOPIC)
    public void listen(ReleasedEquipmentMessage message) {
        equipmentService.changeStatus(message.getId(), EquipmentStatus.WAITING);
    }

}
