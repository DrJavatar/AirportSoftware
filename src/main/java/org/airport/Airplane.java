package org.airport;

import org.people.Person;

import java.util.ArrayList;
import java.util.List;


public final class Airplane {

    private String planeId;
    private String desc;
    private String home_airport_code;
    private List<Person> passangers;
    private List<Person> employees;
    private Person pilot;
    private Person coPilot;

    public Airplane(String planeId, String desc, String home_airport_code, Person pilot, Person coPilot) {
        this.planeId = planeId;
        this.desc = desc;
        this.home_airport_code = home_airport_code;
        this.pilot = pilot;
        this.coPilot = coPilot;
        this.passangers = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public Airplane(String planeId, String desc, String home_airport_code) {
        this(planeId, desc, home_airport_code, null, null);
    }

    public String getHomeAirportCode() {
        return home_airport_code;
    }

    public String getPlaneId() {
        return planeId;
    }

    public String getDesc() {
        return desc;
    }

    public List<Person> getPassangers() {
        return passangers;
    }

    public List<Person> getEmployees() {
        return employees;
    }

    public Person getPilot() {
        return pilot;
    }

    public Person getCoPilot() {
        return coPilot;
    }
}
