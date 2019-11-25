package org.people.impl;

import org.people.EmployeeRole;

public class Pilot extends Employee {
    public Pilot(String firstName, String lastName, int socialNumber) {
        super(firstName, lastName, socialNumber, EmployeeRole.PILOT);
    }
}
