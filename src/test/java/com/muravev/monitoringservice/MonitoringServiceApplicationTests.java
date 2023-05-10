package com.muravev.monitoringservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MonitoringServiceApplicationTests {

    @Test
    void contextLoads() {
    }


    public void test() {
        String[] split = ";;".split(";");
        assertEquals(split.length, 0);
    }

}
