package org.airport;

import org.people.Person;

import java.util.ArrayList;
import java.util.List;


public final class Airplane {

    private int planeId;
    private List<Person> passangers;
    private List<Person> employees;
    private Person pilot;
    private Person coPilot;

    public Airplane(int planeId, Person pilot, Person coPilot) {
        this.planeId = planeId;
        this.pilot = pilot;
        this.coPilot = coPilot;
        this.passangers = new ArrayList<>();
        this.employees = new ArrayList<>();
    }
}
