package com.company.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Port {
    private List<Integer> availableVlan = new ArrayList<>();
    public void reserve(int vlan) {
        availableVlan.remove(Integer.valueOf(vlan));
    }

    public void addAvailableVlan(int vlan) {
        availableVlan.add(vlan);
    }


    public List<Integer> getAvailableVlan() {
        return availableVlan;
    }

    public void setAvailableVlan(List<Integer> availableVlan) {
        this.availableVlan = availableVlan;
    }

    public void sort() {
        Collections.sort(availableVlan);
    }
}
