package com.company.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RequestWriter {
    private final static String REQUESTS_CSV_FILE = "output.csv";
    FileWriter writer = new FileWriter(REQUESTS_CSV_FILE);
    public RequestWriter() throws IOException {
        writer.write("request_id,device_id,primary_port,vlan_id\n");
        writer.flush();
    }

    public void write( List<String> line) throws Exception {

        line.stream().forEach(x-> {
            try {
                writer.write(x);
                writer.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.flush();
        writer.close();
    }


}
