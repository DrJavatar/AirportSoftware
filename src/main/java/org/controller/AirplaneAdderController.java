package org.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.airport.Flight;
import org.controller.listviews.PersonListCell;
import org.people.Person;
import org.people.impl.Employee;
import org.people.impl.Passenger;
import org.people.impl.Pilot;
import org.sql.impl.NegatedPilotFilterQuery;
import org.sql.impl.PassengerQuery;
import org.sql.impl.PilotFilterQuery;
import org.sql.impl.UpdatePersonQuery;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AirplaneAdderController implements Initializable {

    @FXML
    private ListView<Person> onFlightList;

    @FXML
    private ListView<Person> personList;

    private Flight flight;

    private Consumer<Void> updateTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        onFlightList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        personList.setCellFactory(new PersonListCell());
        onFlightList.setCellFactory(new PersonListCell());
    }

    public void selectPilots() {
        try {
            ObservableList<Pilot> pilots = new PilotFilterQuery().call();
            personList.getItems().setAll(pilots);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUpdateTable(Consumer<Void> updateTable) {
        this.updateTable = updateTable;
    }

    public void setFlight(Flight flight, boolean pilotSelection) {
        this.flight = flight;
        try {
            if(pilotSelection) {
                selectPilots();
            } else {
                ObservableList<Passenger> passengers = new PassengerQuery().call();
                ObservableList<Employee> employees = new NegatedPilotFilterQuery().call();
                List<Person> persons = new ArrayList<>();
                persons.addAll(passengers);
                persons.addAll(employees);
                ObservableList<Person> onFlightPassengers = flight.getAirplane().getPassengers();
                ObservableList<Person> onFlightEmployees = flight.getAirplane().getEmployees();
                ObservableList<Person> onFlightPersons = FXCollections.concat(onFlightPassengers, onFlightEmployees);
                onFlightList.getItems().setAll(onFlightPersons);
                List<Person> collect = persons.stream().filter(person -> onFlightPersons.stream().noneMatch(person::equals)).collect(Collectors.toList());
                personList.getItems().setAll(collect);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void finish() {
        personList.getScene().getWindow().hide();
    }

    @FXML
    private void addToFlight() {
        List<Person> persons = personList.getSelectionModel().getSelectedItems();
        for(Person person : persons) {
            if (person instanceof Pilot) {
                long count = onFlightList.getItems().stream().filter(person1 -> person1 instanceof Pilot).count();
                if (count >= 2) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.getDialogPane().setStyle("-fx-base: rgb(34,34,34);");
                    alert.setTitle("Error adding pilot");
                    alert.setHeaderText("Pilot/Co-Pilot Found!");
                    alert.setContentText("This aircraft already has a pilot and co-pilot.");
                    alert.getButtonTypes().setAll(ButtonType.OK);
                    alert.getDialogPane().toFront();
                    alert.showAndWait();
                    return;
                }
            }
            personList.getItems().remove(person);
            onFlightList.getItems().add(person);
            person.setFlightNumber(flight.getFlightId());
            if(person instanceof Employee) {
                flight.getAirplane().getEmployees().add(person);
                if(person instanceof Pilot) {
                    if (flight.getAirplane().getPilot() == null) {
                        flight.getAirplane().setPilot(person);
                    } else {
                        flight.getAirplane().setCoPilot(person);
                    }
                }
            } else {
                flight.getAirplane().getPassengers().add(person);
            }
            try {
                new UpdatePersonQuery(person).call();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(updateTable != null) {
            updateTable.accept(null);
        }
    }

    @FXML
    private void removeFromFlight() {
        List<Person> persons = onFlightList.getSelectionModel().getSelectedItems();
        for(Person person : persons) {
            onFlightList.getItems().remove(person);
            personList.getItems().add(person);
            if(person instanceof Employee) {
                flight.getAirplane().getEmployees().remove(person);
            } else {
                flight.getAirplane().getPassengers().remove(person);
            }
        }
        if(updateTable != null) {
            updateTable.accept(null);
        }
    }

}
