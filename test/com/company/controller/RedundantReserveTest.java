package com.company.controller;

import com.company.model.Device;
import com.company.model.Port;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RedundantReserveTest {

    private RedundantReserve redundantReserve = new RedundantReserve();

    public RedundantReserveTest() throws IOException {
    }

    @Test(expected = Exception.class)
    public void whenDevicesEmptyShouldThrowException() throws Exception {
        //when
        redundantReserve.reserve(0, new HashMap<>());
    }

    @Test
    public void whenOneDeviceTest() throws Exception {
        //given
        Device device = new Device();
        Port primaryPort = new Port();
        List<Integer> availableVlan = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        primaryPort.setAvailableVlan(availableVlan);
        device.setPrimary(primaryPort);
        device.setSecondary(primaryPort);
        Map<Integer, Device> devices = new HashMap<>();
        int requestId = 10;
        int deviceId = 1;
        devices.put(deviceId, device);

        //when
        List<String> actual = redundantReserve.reserve(requestId, devices);

        //then
        List<String> expected = new ArrayList<>(List.of("10,1,0,1", "10,1,1,1"));
        assertEquals(expected, actual);
    }
}
