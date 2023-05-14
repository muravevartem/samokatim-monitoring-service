package com.muravev.monitoringservice.controller;

import com.muravev.monitoringservice.http.service.HttpHandler;
import com.muravev.monitoringservice.model.in.MonitoringRecordIn;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/monitoring")
@RequiredArgsConstructor
public class RestMonitoringController {
    private final HttpHandler httpHandler;


    @PostMapping
    public void save(@RequestBody @Valid MonitoringRecordIn record) {
        httpHandler.handle(record);
    }
}
