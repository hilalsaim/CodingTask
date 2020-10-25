package com.company.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceBuilder {
    private Map<Integer, Device> devices = new HashMap<>();

    public DeviceBuilder() {
    }

    public DeviceBuilder withPrimaryVlan(int deviceId, List<Integer> primaryVlan) {
        Device device = new Device();
        device.addVlan(1, primaryVlan);
        devices.put(deviceId, device);
        return this;
    }

    public DeviceBuilder withPrimaryAndSecondaryVlan(int deviceId, List<Integer> primaryVlan, List<Integer> secondaryVlan) {
        Device device = new Device();
        device.addVlan(1, primaryVlan);
        device.addVlan(0, secondaryVlan);
        devices.put(deviceId, device);
        return this;
    }

    public DeviceBuilder withPrimaryAndSecondarySameVlan(int deviceId, List<Integer> vlan) {
        Device device = new Device();
        device.addVlan(1, vlan);
        device.addVlan(0, vlan);
        devices.put(deviceId, device);
        return this;
    }

    public Map<Integer, Device> build() {
        return devices;
    }
}
