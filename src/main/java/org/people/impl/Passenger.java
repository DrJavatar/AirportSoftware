package org.people.impl;

import org.people.Person;

public class Passenger extends Person {

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
}
