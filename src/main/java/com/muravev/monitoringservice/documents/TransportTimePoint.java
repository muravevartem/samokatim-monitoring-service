package com.muravev.monitoringservice.documents;

import com.muravev.monitoringservice.enums.TransportStatus;
import com.muravev.monitoringservice.enums.TransportType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document("transport")
public class Transport {
    @Id
    private UUID id;

    private long transportId;

    private TransportStatus status;

    private double lat;

    private double lng;

    private LocalDateTime timestamp;

}
