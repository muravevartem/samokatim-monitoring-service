package com.muravev.monitoringservice.dao;

import com.muravev.monitoringservice.documents.TransportTimePoint;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface TransportRepository extends ReactiveMongoRepository<TransportTimePoint, UUID> {
    Flux<TransportTimePoint> findAllByTransportIdOrderByTimestamp(long transportId);

    Mono<TransportTimePoint> findFirstByTransportIdOrderByTimestampDesc(long transportId);
}
