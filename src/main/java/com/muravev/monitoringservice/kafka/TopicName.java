package com.muravev.monitoringservice.kafka;

public interface TopicName {
    String REGISTERED_EQUIPMENT_TOPIC = "ru.muravev.samokatim.equipment.registered";
    String BOOKED_EQUIPMENT_TOPIC = "ru.muravev.samokatim.equipment.booked";
    String RELEASED_EQUIPMENT_TOPIC = "ru.muravev.samokatim.equipment.released";
    String COMPLETED_RENT_TOPIC = "ru.muravev.samokatim.rent.completed";
    String COMPLETED_TRACK_TOPIC = "ru.muravev.samokatim.track.completed";
}
