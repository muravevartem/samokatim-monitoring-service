package com.muravev.monitoringservice.http.service;

import com.muravev.monitoringservice.model.in.MonitoringRecordIn;

public interface HttpHandler {
    void handle(MonitoringRecordIn record);
}
