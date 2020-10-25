package com.company.controller;

import com.company.model.Device;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NonRedundantReserve {

    public NonRedundantReserve() throws IOException {
    }

    public List<String> reserve(Integer key, Map<Integer, Device> devices) throws Exception {
        if (devices.isEmpty()) {
            throw new Exception("Empty device list");
        }

        int minDevice = Integer.MAX_VALUE;
        int minVlan = Integer.MAX_VALUE;

        for ( Map.Entry<Integer, Device> device : devices.entrySet()) {
            if (minVlan > device.getValue().getPrimary().getAvailableVlan().get(0)) {
                minVlan = device.getValue().getPrimary().getAvailableVlan().get(0);
                minDevice = device.getKey();
            } else if (minVlan == device.getValue().getPrimary().getAvailableVlan().get(0)) {
                if (minDevice > device.getKey()) {
                    minDevice = device.getKey();
                }
            }
        }

        devices.get(minDevice).getPrimary().reserve(minVlan);
        String output = String.format("%d,%d,%d,%d", key, minDevice,1, minVlan);
        return Arrays.asList(output);
    }
}
