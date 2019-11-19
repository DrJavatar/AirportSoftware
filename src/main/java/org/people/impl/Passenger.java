package org.people.impl;

import org.people.Person;

public class Passenger extends Person {

    public Passenger(String firstName, String lastName, int socialNumber) {
        super(firstName, lastName, socialNumber);
    }

    @Override
    public boolean updateDatabase() {
        //TODO - update passenger tables
        return false;
    }
}
