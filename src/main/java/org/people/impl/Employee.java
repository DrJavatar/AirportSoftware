package org.people.impl;

import org.people.EmployeeRole;

public class Employee extends Passenger {

    private EmployeeRole role;

    public Employee(String firstName, String lastName, int socialNumber, EmployeeRole role) {
        super(firstName, lastName, socialNumber);
        this.role = role;
    }

    public EmployeeRole getRole() {
        return role;
    }
}
