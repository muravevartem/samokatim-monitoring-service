package com.muravev.monitoringservice.dummy.jobs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muravev.monitoringservice.dao.TransportRepository;
import com.muravev.monitoringservice.documents.TransportTimePoint;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointGenerator {
    private final TransportRepository transportRepository;
    private final ObjectMapper mapper;


    @SneakyThrows
    @Scheduled(fixedRate = 6000L)
    public void addPoint() {
        int transport = new Random().nextInt(10, 1000);
        log.info("transport: {}", transport);
        List<Point> points = mapper.readValue(new File("/dummy-files/route1.json"), new TypeReference<List<Point>>() {
        });
        for (var point : points) {
            var timePoint = new TransportTimePoint()
                    .setTransportId(transport)
                    .setLng(point.getLon())
                    .setLat(point.getLat())
                    .setId(UUID.randomUUID())
                    .setTimestamp(LocalDateTime.now());
            transportRepository.save(timePoint)
                    .block();
            Thread.sleep(Duration.ofSeconds(30L).toMillis());
        }
    }

}

@Data
class Point {
    private double lat;
    private double lon;
}
