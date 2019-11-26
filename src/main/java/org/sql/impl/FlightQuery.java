package org.sql.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.airport.Airplane;
import org.airport.Airport;
import org.airport.Flight;
import org.controller.AppController;
import org.people.Person;
import org.people.impl.Employee;
import org.people.impl.Passenger;
import org.people.impl.Pilot;
import org.sql.SQLCallback;
import org.sql.SQLManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightQuery implements SQLCallback<Flight> {
    @Override
    public ObservableList<Flight> call() throws SQLException {
        List<Flight> flights = new ArrayList<>();
        Connection con = SQLManager.getSqlManager().createConnection();

        ResultSet resultSet = con.prepareStatement("SELECT * FROM Flights;").executeQuery();

        while (resultSet.next()) {
            int flightId = resultSet.getInt("flight_id");
            String aircraftType = resultSet.getString("aircraft_type");
            String airport_code = resultSet.getString("airport_code");
            String airport_code_dest = resultSet.getString("airport_code_dest");
            int pilotId = resultSet.getInt("pilot_id");
            int coPilotId = resultSet.getInt("co_pilot_id");
            if(AppController.AIRPORTS.containsKey(airport_code)) {
                Airport airport = AppController.AIRPORTS.get(airport_code);
                Airplane airplane = airport.getAirplane(aircraftType);
                ObservableList<Pilot> pilots = new PilotFilterQuery().call();
                ObservableList<Passenger> passengers = new PassengerQuery().call();
                ObservableList<Employee> employees = new NegatedPilotFilterQuery().call();
                ObservableList<Person> persons = FXCollections.observableArrayList();
                persons.addAll(passengers);
                persons.addAll(employees);
                persons.addAll(pilots);
                for(Person p : persons) {
                    if(p.getFlightNumber() == flightId) {
                        if(p instanceof Employee) {
                            airplane.getEmployees().add(p);
                        } else if(p instanceof Passenger) {
                            airplane.getPassengers().add(p);
                        }
                    }
                }
                for(Pilot p : pilots) {
                    if(p.getSocialNumber() == pilotId) {
                        airplane.setPilot(p);
                    } else if(p.getSocialNumber() == coPilotId) {
                        airplane.setCoPilot(p);
                    }
                }
                Flight flight = new Flight(flightId, airplane, airport_code, airport_code_dest);
                airport.launchFlight(flight);
                flights.add(flight);
            }
        }

        con.close();
        return FXCollections.observableArrayList(flights);
    }
}
