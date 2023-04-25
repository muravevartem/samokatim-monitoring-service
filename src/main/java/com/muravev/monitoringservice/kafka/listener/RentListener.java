package com.muravev.monitoringservice.kafka.listener;

import com.muravev.monitoringservice.kafka.TopicName;
import com.muravev.monitoringservice.model.response.EquipmentTrackResponse;
import com.muravev.monitoringservice.service.EquipmentTracker;
import com.muravev.samokatimmessage.CompletedRentMessage;
import com.muravev.samokatimmessage.CompletedTrackItem;
import com.muravev.samokatimmessage.CompletedTrackMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RentListener {
    private final EquipmentTracker tracker;

    @SendTo(TopicName.COMPLETED_TRACK_TOPIC)
    @KafkaListener(topics = TopicName.COMPLETED_RENT_TOPIC)
    public CompletedTrackMessage handle(CompletedRentMessage message) {
        List<EquipmentTrackResponse> track = tracker.getByEquipment(message.getEquipmentId(),
                ZonedDateTime.parse(message.getStartTime(), DateTimeFormatter.ISO_DATE_TIME),
                ZonedDateTime.parse(message.getEndTime(), DateTimeFormatter.ISO_DATE_TIME));
        List<CompletedTrackItem> trackItems = track.stream()
                .map(point -> CompletedTrackItem.newBuilder()
                        .setLat(point.getLat())
                        .setLng(point.getLng())
                        .setTime(point.getCreatedDate().format(DateTimeFormatter.ISO_DATE_TIME))
                        .build())
                .toList();
        return CompletedTrackMessage.newBuilder()
                .setRentId(message.getRentId())
                .setTrack(trackItems)
                .build();
    }
}
