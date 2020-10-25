package com.company.controller;

import com.company.model.Device;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RequestReader {
    private final static String REQUESTS_CSV_FILE = "requests.csv";

    public Map<Integer, Integer> read() {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        Map<Integer, Integer> requests = new HashMap<>();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(REQUESTS_CSV_FILE).getFile());

        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] list = line.split(cvsSplitBy);
                int requestsId = Integer.valueOf(list[0]);
                int redundant = Integer.valueOf(list[1]);
                requests.put(requestsId, redundant);
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

        return requests;
    }
}
