package com.company.controller;

import com.company.model.Device;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RedundantReserve {
    RequestWriter wrt=new RequestWriter();

    public RedundantReserve() throws IOException {
    }

    public List<String> reserve(Integer key, Map<Integer, Device> devices) throws Exception {

        if (devices.isEmpty()) {
            throw new Exception("Empty device list");
        }

        int minDevice = Integer.MAX_VALUE;
        int minVlan = Integer.MAX_VALUE;

        for ( Map.Entry<Integer, Device> device : devices.entrySet()) {
            for (int vlanPrimary : device.getValue().getPrimary().getAvailableVlan()) {
                if (vlanPrimary <= minVlan) {
                    for (int vlanSecondary : device.getValue().getSecondary().getAvailableVlan()) {
                        if (vlanPrimary == vlanSecondary) {
                            if (vlanPrimary < minVlan) {
                                minDevice = device.getKey();
                            } else if (vlanPrimary == minVlan) {
                                if (device.getKey() < minDevice) {
                                    minDevice = device.getKey();
                                }
                            }
                            minVlan = vlanPrimary;
                        } else if (vlanPrimary < vlanSecondary) {
                            break;
                        }
                    }
                } else if (minVlan < vlanPrimary) {
                    break;
                }
            }
        }

        devices.get(minDevice).getPrimary().reserve(minVlan);
        devices.get(minDevice).getSecondary().reserve(minVlan);

        String outputPrimary = String.format("%d,%d,%d,%d", key, minDevice, 0, minVlan);
        String outputSecondary = String.format("%d,%d,%d,%d", key, minDevice, 1, minVlan);

        return new ArrayList<>(List.of(outputPrimary, outputSecondary));
    }
}
