package com.muravev.monitoringservice.services;

import com.muravev.monitoringservice.documents.TransportTimePoint;
import com.muravev.monitoringservice.models.TransportPoint;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransportService {
    Flux<TransportTimePoint> getTrack(long transportId);

    Mono<TransportTimePoint> lastPoint(long transportId);

    Mono<TransportTimePoint> savePoint(TransportPoint point);
}
