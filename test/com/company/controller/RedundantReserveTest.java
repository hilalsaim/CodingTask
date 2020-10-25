package com.company.controller;

import com.company.model.DeviceBuilder;
import com.company.model.Device;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

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
    public void whenOneDeviceToReserveShouldSelectLowestAvailableVlan() throws Exception {
        //given
        DeviceBuilder deviceBuilder = new DeviceBuilder();
        List<Integer> availableVlan = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        Map<Integer, Device> devices = deviceBuilder
                .withPrimaryAndSecondarySameVlan(1, availableVlan)
                .build();

        int requestId = 10;

        //when
        List<String> actual = redundantReserve.reserve(requestId, devices);

        //then
        List<String> expected = new ArrayList<>(List.of("10,1,0,1", "10,1,1,1"));
        assertEquals(expected, actual);
    }

    @Test
    public void whenMoreDevicesToReserveShouldSelectDeviceWithLowestAvailableVlan() throws Exception {
        //given
        DeviceBuilder deviceBuilder = new DeviceBuilder();
        List<Integer> availableVlanDevice1 = new ArrayList<>(List.of(3, 4, 5, 6, 7));
        List<Integer> availableVlanDevice2 = new ArrayList<>(List.of(2, 3, 4, 5, 6));
        Map<Integer, Device> devices = deviceBuilder
                .withPrimaryAndSecondarySameVlan(1, availableVlanDevice1)
                .withPrimaryAndSecondarySameVlan(2, availableVlanDevice2)
                .build();

        int requestId = 10;

        //when
        List<String> actual = redundantReserve.reserve(requestId, devices);

        //then
        List<String> expected = new ArrayList<>(List.of("10,2,0,2", "10,2,1,2"));
        assertEquals(expected, actual);
    }

    @Test
    public void whenMoreDevicesToReserveShouldSelectDeviceWithLowestAvailableVlanWhichHasSameSecondaryVlanAvailable()
            throws Exception {
        //given
        DeviceBuilder deviceBuilder = new DeviceBuilder();
        List<Integer> primaryVlanDevice1 = new ArrayList<>(List.of(2, 4, 5, 6, 7));
        List<Integer> secondaryVlanDevice1 = new ArrayList<>(List.of(3, 4, 5, 6, 7));
        List<Integer> primaryVlanDevice2 = new ArrayList<>(List.of(2, 3, 5, 6, 7));
        List<Integer> secondaryVlanDevice2 = new ArrayList<>(List.of(1, 3, 5, 6, 7));
        Map<Integer, Device> devices = deviceBuilder
                .withPrimaryAndSecondaryVlan(1, primaryVlanDevice1, secondaryVlanDevice1)
                .withPrimaryAndSecondaryVlan(2, primaryVlanDevice2, secondaryVlanDevice2)
                .build();

        int requestId = 10;

        //when
        List<String> actual = redundantReserve.reserve(requestId, devices);

        //then
        List<String> expected = new ArrayList<>(List.of("10,2,0,3", "10,2,1,3"));
        assertEquals(expected, actual);
    }

    @Test
    public void whenSameLowestVlanAvailableShouldSelectLowestDeviceId() throws Exception {
        //given
        DeviceBuilder deviceBuilder = new DeviceBuilder();
        List<Integer> vlanDevice1 = new ArrayList<>(List.of(2, 4, 5, 6, 7));
        List<Integer> vlanDevice2 = new ArrayList<>(List.of(2, 3, 5, 6, 7));
        Map<Integer, Device> devices = deviceBuilder
                .withPrimaryAndSecondarySameVlan(2, vlanDevice1)
                .withPrimaryAndSecondarySameVlan(1, vlanDevice2)
                .build();

        int requestId = 10;

        //when
        List<String> actual = redundantReserve.reserve(requestId, devices);

        //then
        List<String> expected = new ArrayList<>(List.of("10,1,0,2", "10,1,1,2"));
        assertEquals(expected, actual);
    }
}
