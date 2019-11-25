package org.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.airport.Airplane;
import org.airport.Airport;
import org.airport.Flight;
import org.controller.table.StringCellFactory;
import org.sql.impl.AircraftQuery;
import org.sql.impl.AirportQuery;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aircraftCol.setCellFactory(new StringCellFactory());
        airportCode.setCellFactory(new StringCellFactory());
        airportCodeDest.setCellFactory(new StringCellFactory());
        passCount.setCellFactory(new StringCellFactory());

        try {
            List<Airport> airports = new AirportQuery().call();
            airports.forEach(airport -> AIRPORTS.put(airport.getCode(), airport));
            List<Airplane> list = new AircraftQuery().call();
            aircraftTable.getItems().setAll(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        aircraftCol.setCellValueFactory(ascdf -> new SimpleStringProperty(ascdf.getValue().getPlaneId()));
        airportCode.setCellValueFactory(ascdf -> new SimpleStringProperty(ascdf.getValue().getHomeAirportCode()));
        airportCodeDest.setCellValueFactory(ascdf -> {
            final Airplane plane = ascdf.getValue();
            Airport airport = AIRPORTS.get(plane.getHomeAirportCode());
            Optional<Flight> flight = airport.getActiveFlight(plane);
            if(flight.isPresent()) {
                Flight f = flight.get();
                return new SimpleStringProperty(f.getAirport_code_dest());
            } else {
                return new SimpleStringProperty("Not in flight");
            }
        });
    }
}
