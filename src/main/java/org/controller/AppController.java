package org.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import org.airport.Airplane;
import org.airport.Airport;
import org.airport.Flight;
import org.controller.choiceboxes.ViewType;
import org.controller.listviews.PersonListCell;
import org.controller.table.StringCellFactory;
import org.people.Person;
import org.people.impl.Employee;
import org.people.impl.Passenger;
import org.sql.SQLConnectionConfig;
import org.sql.impl.*;

import java.io.IOException;
import java.net.Socket;
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

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField sqlHost;

    @FXML
    private TextField sqlUser;

    @FXML
    private PasswordField sqlPass;

    @FXML
    private CheckBox rememberMe;

    @FXML
    private Spinner<Integer> sqlPort;

    @FXML
    private Label conLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aircraftCol.setCellFactory(new StringCellFactory());
        airportCode.setCellFactory(new StringCellFactory());
        airportCodeDest.setCellFactory(new StringCellFactory());
        passCount.setCellFactory(new StringCellFactory());
        personList.setCellFactory(new PersonListCell());
        sqlPort.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 65535));
        conLabel.setText("Not tested.");
        conLabel.setTextFill(Color.BLACK);
        sqlHost.setText(SQLConnectionConfig.SQL_CONNECTION_CONFIG.getHost());
        sqlUser.setText(SQLConnectionConfig.SQL_CONNECTION_CONFIG.getUser());
        sqlPass.setText(SQLConnectionConfig.SQL_CONNECTION_CONFIG.getPass());
        sqlPort.getValueFactory().setValue(SQLConnectionConfig.SQL_CONNECTION_CONFIG.getPort());
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
            setPersonList(t1);
        });

        aircraftTable.getSelectionModel().selectedItemProperty().addListener((observableValue, airplane, t1) -> {
            if (t1 != null) {
                personType.setValue(ViewType.AIRCRAFT_LIST);
            } else {
                return;
            }
            personList.getItems().clear();
            List<Person> persons = new ArrayList<>();
            persons.addAll(t1.getEmployees());
            persons.addAll(t1.getPassangers());
            if (!persons.isEmpty()) {
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

        personList.getSelectionModel().selectedItemProperty().addListener((observableValue, person, t1) -> {
            if (t1 != null) {
                firstNameField.setText(t1.getFirstName());
                lastNameField.setText(t1.getLastName());
                if(t1 instanceof Passenger) {
                    phoneField.setDisable(false);
                    addressField.setDisable(false);
                    phoneField.setText(((Passenger) t1).getPhone());
                    addressField.setText(((Passenger) t1).getAddress());
                } else {
                    phoneField.setText("");
                    addressField.setText("");
                    phoneField.setDisable(true);
                    addressField.setDisable(true);
                }
            }
        });

        searchPersons.textProperty().addListener((observableValue, s, t1) -> {
            if(t1.isEmpty()) {
                personList.getItems().clear();
                setPersonList(personType.getValue());
            }
        });
        searchPersons.setOnAction(actionEvent -> {
            try {
                ViewType type = personType.getValue();
                switch (type) {

                    case PASSENGERS:
                        List<Passenger> passengers = new PassengerSearchQuery(searchPersons.getText()).call();
                        aircraftTable.getSelectionModel().clearSelection();
                        personList.getItems().setAll(passengers);
                        break;
                    case EMPLOYEES:
                        List<Employee> employees = new StaffSearchQuery(searchPersons.getText()).call();
                        aircraftTable.getSelectionModel().clearSelection();
                        personList.getItems().setAll(employees);
                        break;
                    case ALL:
                        List<Person> persons = new ArrayList<>();
                        persons.addAll(new PassengerSearchQuery(searchPersons.getText()).call());
                        persons.addAll(new StaffSearchQuery(searchPersons.getText()).call());
                        aircraftTable.getSelectionModel().clearSelection();
                        personList.getItems().setAll(persons);
                        break;

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }

    private void setPersonList(ViewType t1) {
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
    }

    @FXML
    private void testConnection() {
        try {
            new Socket(sqlHost.getText(), sqlPort.getValue()).close();
            conLabel.setText("Connection successful");
            conLabel.setTextFill(Color.GREEN);
        } catch (IOException e) {
            conLabel.setText("Connection Failed");
            conLabel.setTextFill(Color.RED);
        }
    }

    @FXML
    private void updateSQLConfig() {
        SQLConnectionConfig config = SQLConnectionConfig.SQL_CONNECTION_CONFIG;
        if (!sqlHost.getText().equals(config.getHost()) && !sqlHost.getText().isEmpty()) {
            config.setHost(sqlHost.getText());
        }
        if(!sqlUser.getText().equals(config.getUser()) && !sqlUser.getText().isEmpty()) {
            config.setUser(sqlUser.getText());
        }
        if(!sqlPass.getText().equals(config.getPass()) && !sqlPass.getText().isEmpty()) {
            config.setPass(sqlPass.getText());
        }
        if(sqlPort.getValue() != config.getPort()) {
            int port = sqlPort.getValue();
            if(port <= 1023) {
                port = 3306;
            }
            config.setPort(port);
        }
    }

    @FXML
    private void updatePerson() {


    }

}
