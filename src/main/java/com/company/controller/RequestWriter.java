package com.company.controller;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestWriter {
    private final static String REQUESTS_CSV_FILE = "output.csv";
    //CSVWriter writer = new CSVWriter(new FileWriter(REQUESTS_CSV_FILE,false),',','\0');
    FileWriter writer = new FileWriter(REQUESTS_CSV_FILE);
    public RequestWriter() throws IOException {
        writer.write("request_id,device_id,primary_port,vlan_id\n");
        writer.flush();
    }

    public void write( List<String> line2) throws Exception {
        //String output = String.format("%d,%d,%d,%d", request_id, device_id, primary_port, vlan_id);


        line2.stream().forEach(x-> {
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
