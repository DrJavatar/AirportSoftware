package org.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.airport.Airplane;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

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

    }
}
