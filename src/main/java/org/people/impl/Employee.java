package org.people.impl;

public class Employee extends Passenger {
    public Employee(String firstName, String lastName, int socialNumber) {
        super(firstName, lastName, socialNumber);
    }

    @Override
    public boolean updateDatabase() {
        //TODO - update employee tables
        return super.updateDatabase();
    }
}
