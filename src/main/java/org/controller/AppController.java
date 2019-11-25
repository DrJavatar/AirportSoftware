package org.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.airport.Airplane;
import org.airport.Airport;
import org.airport.Flight;
import org.controller.choiceboxes.ViewType;
import org.controller.listviews.PersonListCell;
import org.controller.table.StringCellFactory;
import org.people.Person;
import org.people.impl.Employee;
import org.people.impl.Passenger;
import org.sql.impl.AircraftQuery;
import org.sql.impl.AirportQuery;
import org.sql.impl.PassengerQuery;
import org.sql.impl.StaffQuery;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class AppController implements Initializable {

    public final static Map<String, Airport> AIRPORTS = new HashMap<>();

    @FXML
    private TableView<Airplane> aircraftTable;
    @FXML
    private TableColumn<Airplane, String> aircraftCol;
    @FXML
    private TableColumn<Airplane, String> airportCode;
    @FXML
    private TableColumn<Airplane, String> airportCodeDest;
    @FXML
    private TableColumn<Airplane, String> passCount;

    @FXML
    private TextField searchPersons;

    @FXML
    private ListView<Person> personList;

    @FXML
    private ChoiceBox<ViewType> personType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aircraftCol.setCellFactory(new StringCellFactory());
        airportCode.setCellFactory(new StringCellFactory());
        airportCodeDest.setCellFactory(new StringCellFactory());
        passCount.setCellFactory(new StringCellFactory());
        personList.setCellFactory(new PersonListCell());
        try {
            personType.getItems().setAll(ViewType.values());
            List<Airport> airports = new AirportQuery().call();
            airports.forEach(airport -> AIRPORTS.put(airport.getCode(), airport));
            List<Airplane> list = new AircraftQuery().call();
            aircraftTable.getItems().setAll(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        personType.getSelectionModel().selectedItemProperty().addListener((observableValue, viewType, t1) -> {
            switch (t1) {
                case ALL:
                    try {
                        aircraftTable.getSelectionModel().clearSelection();
                        List<Passenger> passengers = new PassengerQuery().call();
                        List<Employee> employees = new StaffQuery().call();
                        List<Person> persons = new ArrayList<>();
                        persons.addAll(passengers);
                        persons.addAll(employees);
                        personList.getItems().setAll(persons);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case EMPLOYEES:
                    try {
                        aircraftTable.getSelectionModel().clearSelection();
                        personList.getItems().setAll(new StaffQuery().call());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case PASSENGERS:
                    try {
                        aircraftTable.getSelectionModel().clearSelection();
                        personList.getItems().setAll(new PassengerQuery().call());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        });

        aircraftTable.getSelectionModel().selectedItemProperty().addListener((observableValue, airplane, t1) -> {
            if(t1 != null) {
                personType.setValue(ViewType.AIRCRAFT_LIST);
            } else {
                return;
            }
            personList.getItems().clear();
            List<Person> persons = new ArrayList<>();
            persons.addAll(t1.getEmployees());
            persons.addAll(t1.getPassangers());
            if(!persons.isEmpty()) {
                personList.getItems().setAll(persons);
            }
        });

        aircraftCol.setCellValueFactory(ascdf -> new SimpleStringProperty(ascdf.getValue().getPlaneId()));
        airportCode.setCellValueFactory(ascdf -> new SimpleStringProperty(ascdf.getValue().getHomeAirportCode()));
        airportCodeDest.setCellValueFactory(ascdf -> {
            final Airplane plane = ascdf.getValue();
            Airport airport = AIRPORTS.get(plane.getHomeAirportCode());
            Optional<Flight> flight = airport.getActiveFlight(plane);
            if (flight.isPresent()) {
                Flight f = flight.get();
                return new SimpleStringProperty(f.getAirport_code_dest());
            } else {
                return new SimpleStringProperty("Not in flight");
            }
        });
    }

    @FXML
    private void updatePerson() {


    }

}
