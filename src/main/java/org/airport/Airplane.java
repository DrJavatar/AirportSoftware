package org.airport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.people.Person;
import org.people.impl.Pilot;

import java.util.ArrayList;
import java.util.List;


public final class Airplane {

    private String planeId;
    private String desc;
    private String home_airport_code;
    private ObservableList<Person> passengers;
    private ObservableList<Person> employees;
    private Person pilot;
    private Person coPilot;

    public Airplane(String planeId, String desc, String home_airport_code, Person pilot, Person coPilot) {
        this.planeId = planeId;
        this.desc = desc;
        this.home_airport_code = home_airport_code;
        this.pilot = pilot;
        this.coPilot = coPilot;
        this.passengers = FXCollections.observableArrayList();
        this.employees = FXCollections.observableArrayList();
    }

    public Airplane(String planeId, String desc, String home_airport_code) {
        this(planeId, desc, home_airport_code, null, null);
    }

    public boolean hasPilots() {
        if(employees.isEmpty()) {
            return false;
        }
        long count = employees.stream().filter(person -> person instanceof Pilot).count();
        System.out.println("Count " + count);
        return count >= 2;
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

    public ObservableList<Person> getPassengers() {
        return passengers;
    }

    public ObservableList<Person> getEmployees() {
        return employees;
    }

    public Person getPilot() {
        return pilot;
    }

    public Person getCoPilot() {
        return coPilot;
    }

    public void setPilot(Person pilot) {
        this.pilot = pilot;
    }

    public void setCoPilot(Person coPilot) {
        this.coPilot = coPilot;
    }
}
