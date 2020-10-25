package com.company.model;

public class Device {

    public Device() {
    }

    public Device(Port primary) {
        this.primary = primary;
    }

    public Device(Port primary, Port secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    private Port primary = new Port();
    private Port secondary = new Port();

    public Port getPrimary() {
        return primary;
    }

    public void setPrimary(Port primary) {
        this.primary = primary;
    }

    public Port getSecondary() {
        return secondary;
    }

    public void setSecondary(Port secondary) {
        this.secondary = secondary;
    }

    public boolean hasSecondaryPort() {
        return (secondary.getAvailableVlan().size() > 0);
    }

    public void addVlan(int port, int vlan) {
        if(port == 1) {
            primary.addAvailableVlan(vlan);
        } else if(port == 0) {
            secondary.addAvailableVlan(vlan);
        }
    }

    public void sort() {
        primary.sort();
        secondary.sort();
    }

}
