package org.people.impl;

public class Pilot extends Employee {
    public Pilot(String firstName, String lastName, int socialNumber) {
        super(firstName, lastName, socialNumber);
    }

    @Override
    public boolean updateDatabase() {
        //TODO - update pilot tables
        return super.updateDatabase();
    }
}
