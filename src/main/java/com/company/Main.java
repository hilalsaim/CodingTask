package com.company;

import com.company.controller.*;
import com.company.model.Device;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {

        NonRedundantReserve nonRedundantReserve = new NonRedundantReserve();
        RedundantReserve redundantReserve = new RedundantReserve();

        DeviceReader deviceReader = new DeviceReader();
        RequestReader requestReader = new RequestReader();
        Map<Integer, Device> devices = deviceReader.read();
        Map<Integer, Integer> requests= requestReader.read();
        List<String> listCsv = new ArrayList();
        devices.values().stream().forEach(Device::sort);

        for (Map.Entry<Integer, Integer> request : requests.entrySet()) {
            if(request.getValue() == 1) {
               listCsv.addAll(redundantReserve.reserve(request.getKey(), devices));
            } else if (request.getValue() == 0) {
               listCsv.addAll(nonRedundantReserve.reserve(request.getKey(), devices));
            }
        }
         RequestWriter wrt=new RequestWriter();
         wrt.write(listCsv);
    }
}
