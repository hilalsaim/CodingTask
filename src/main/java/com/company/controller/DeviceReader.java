package com.company.controller;

import com.company.model.Device;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DeviceReader {
    private final static String VLANS_CSV_FILE = "vlans.csv";

    public Map<Integer, Device> read() {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        Map<Integer, Device> devices = new HashMap<>();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(VLANS_CSV_FILE).getFile());

        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] list = line.split(cvsSplitBy);
                int deviceId = Integer.valueOf(list[0]);
                int primaryPort = Integer.valueOf(list[1]);
                int availableVlan = Integer.valueOf(list[2]);
                if(!devices.containsKey(deviceId)) {
                    devices.put(deviceId, new Device());
                }
                devices.get(deviceId).addVlan(primaryPort, availableVlan);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return devices;
    }
}
