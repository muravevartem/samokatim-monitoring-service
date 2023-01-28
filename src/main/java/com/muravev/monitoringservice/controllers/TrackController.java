package com.muravev.monitoringservice.controllers;

import com.muravev.monitoringservice.documents.TransportTimePoint;
import com.muravev.monitoringservice.models.TransportPoint;
import com.muravev.monitoringservice.services.TransportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/track")
@RequiredArgsConstructor
public class TransportController {
    private final TransportService transportService;


    @GetMapping
    public Flux<TransportTimePoint> getTrack(@RequestParam("transport") Long transportId) {
        return transportService.getTrack(transportId);
    }

    @PostMapping
    public Mono<TransportTimePoint> savePoint(@RequestBody @Valid TransportPoint point) {
        return transportService.savePoint(point);
    }
}
