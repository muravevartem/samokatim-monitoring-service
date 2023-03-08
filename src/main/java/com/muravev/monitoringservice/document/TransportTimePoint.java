package com.muravev.monitoringservice.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Document("transport")
public class TransportTimePoint {
    @Id
    private UUID id;

    private long transportId;

    private double lat;

    private double lng;

    private LocalDateTime timestamp;

    @Override
    public String toString() {
        return "TransportTimePoint{" +
                "id=" + id +
                '}';
    }
}
