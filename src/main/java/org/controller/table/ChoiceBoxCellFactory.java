package org.controller.table;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.airport.Airplane;
import org.airport.Airport;
import org.airport.Flight;
import org.controller.AirplaneAdderController;
import org.controller.AppController;
import org.sql.impl.DeleteFlightQuery;
import org.sql.impl.LaunchFlightQuery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class ChoiceBoxCellFactory extends TableCell<Airplane, String> implements Callback<TableColumn<Airplane, String>, TableCell<Airplane, String>> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        super.setText(null);
        if (!empty && item != null) {
            HBox box = new HBox();
            box.setStyle("-fx-base: rgb(34,34,34)");
            ChoiceBox<Airport> airports = new ChoiceBox<>();
            Button clear = new Button("-");
            clear.setTooltip(new Tooltip("Clear Airport code"));
            clear.setOnAction(actionEvent -> {
                try {
                    Airplane plane = this.getTableRow().getItem();
                    Airport airport = AppController.AIRPORTS.get(this.getTableRow().getItem().getHomeAirportCode());
                    airport.removeFlight(plane);
                    airports.getSelectionModel().clearSelection();
                    new DeleteFlightQuery(plane).call();
                    this.getTableView().refresh();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            airports.getItems().setAll(AppController.AIRPORTS.values());
            airports.setConverter(new StringConverter<Airport>() {
                @Override
                public String toString(Airport airport) {
                    return airport.getCode();
                }

                @Override
                public Airport fromString(String s) {
                    return AppController.AIRPORTS.get(s);
                }
            });
            if(this.getTableRow().getItem() == null) {
                return;
            }
            final Airport apt = AppController.AIRPORTS.get(this.getTableRow().getItem().getHomeAirportCode());
            Optional<Flight> activeFlight = apt.getActiveFlight(this.getTableRow().getItem());
            activeFlight.ifPresent(flight -> airports.setValue(AppController.AIRPORTS.get(flight.getAirport_code_dest())));
            airports.getSelectionModel().selectedItemProperty().addListener((observableValue, airport, t1) -> {
                if(t1 != null) {
                    Airplane plane = this.getTableRow().getItem();
                    Airport air = AppController.AIRPORTS.get(plane.getHomeAirportCode());
                    if (plane.hasPilots()) {
                        Optional<Flight> flight = air.launchFlight(plane, t1.getCode());
                        if(flight.isPresent()) {
                            try {
                                new LaunchFlightQuery(flight.get()).call();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        try {
                            FXMLLoader loader = new FXMLLoader(AppController.class.getClassLoader().getResource("fxml/addpersons.fxml"));
                            Parent parent = loader.load();
                            AirplaneAdderController controller = loader.getController();
                            Stage stage = new Stage();
                            stage.setTitle("Edit Passenger/Employee List");
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.initStyle(StageStyle.UTILITY);
                            stage.setScene(new Scene(parent));
                            Flight flight = new Flight(plane, plane.getHomeAirportCode(), t1.getCode());
                            controller.setFlight(flight, true);
                            stage.showAndWait();
                            if(plane.hasPilots()) {
                                air.launchFlight(flight);
                                new LaunchFlightQuery(flight).call();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Pilot Selection");
                                alert.setHeaderText("Pilot Selection Problem");
                                alert.setContentText("You need to select a pilot and co-pilot.");
                                alert.getButtonTypes().setAll(ButtonType.OK);
                                alert.getDialogPane().setStyle("-fx-base: rgb(34,34,34);");
                                alert.showAndWait();
                            }
                        } catch (IOException | SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            box.setAlignment(Pos.CENTER);
            box.setSpacing(3);
            box.getChildren().setAll(airports, clear);
            super.setGraphic(box);
        } else {
            super.setGraphic(null);
        }
    }

    @Override
    public TableCell<Airplane, String> call(TableColumn<Airplane, String> column) {
        return new ChoiceBoxCellFactory();
    }
}