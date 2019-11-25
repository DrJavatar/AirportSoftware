package org.people.impl;

import org.people.EmployeeRole;
import org.people.Person;

public class Employee extends Person {

    private EmployeeRole role;

    public Employee(String firstName, String lastName, int socialNumber, EmployeeRole role) {
        super(firstName, lastName, socialNumber);
        this.role = role;
    }

    public EmployeeRole getRole() {
        return role;
    }
}
