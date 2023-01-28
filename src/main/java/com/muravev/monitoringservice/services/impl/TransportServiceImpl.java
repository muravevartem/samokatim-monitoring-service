package com.muravev.monitoringservice.services.impl;

import com.muravev.monitoringservice.dao.TransportRepository;
import com.muravev.monitoringservice.documents.TransportTimePoint;
import com.muravev.monitoringservice.models.TransportPoint;
import com.muravev.monitoringservice.services.TransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransportServiceImpl implements TransportService {
    private final TransportRepository repository;


    @Override
    public Flux<TransportTimePoint> getTrack(long transportId) {
        return repository.findAllByTransportIdOrderByTimestamp(transportId);
    }

    @Override
    public Mono<TransportTimePoint> lastPoint(long transportId) {
        return repository.findFirstByTransportIdOrderByTimestampDesc(transportId);
    }

    @Override
    public Mono<TransportTimePoint> savePoint(TransportPoint point) {
        TransportTimePoint timePoint = new TransportTimePoint()
                .setId(UUID.randomUUID())
                .setTransportId(point.getTransportId())
                .setLng(point.getLng())
                .setLat(point.getLat())
                .setTimestamp(LocalDateTime.now());
        return repository.save(timePoint);
    }
}
