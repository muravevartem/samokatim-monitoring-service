package com.muravev.monitoringservice.service;

import com.muravev.monitoringservice.document.TransportTimePoint;
import com.muravev.monitoringservice.model.TransportPoint;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransportService {
    Flux<TransportTimePoint> getTrack(long transportId);

    Mono<TransportTimePoint> lastPoint(long transportId);

    Mono<TransportTimePoint> savePoint(TransportPoint point);
}
