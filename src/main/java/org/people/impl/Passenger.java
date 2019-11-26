package org.people.impl;

import org.people.Person;

public final class Passenger extends Person {

    private String address;
    private String phone;

    public Passenger(String firstName, String lastName, int socialNumber, String address, String phone) {
        super(firstName, lastName, socialNumber);
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
