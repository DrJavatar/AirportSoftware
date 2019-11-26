package org.controller.table;

import javafx.beans.binding.Bindings;
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
import org.airport.Airplane;
import org.airport.Airport;
import org.airport.Flight;
import org.controller.AirplaneAdderController;
import org.controller.AppController;

import java.io.IOException;
import java.util.Optional;

public class PassCountCellFactory extends TableCell<Airplane, String> implements Callback<TableColumn<Airplane, String>, TableCell<Airplane, String>> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        super.setText(null);
        if (!empty && item != null) {
            HBox box = new HBox();
            box.setAlignment(Pos.CENTER);
            box.setSpacing(3);
            Label label = new Label(item);
            Button edit = new Button("+");
            edit.setOnAction(actionEvent -> {
                try {
                    FXMLLoader loader = new FXMLLoader(AppController.class.getClassLoader().getResource("fxml/addpersons.fxml"));
                    Parent parent = loader.load();
                    AirplaneAdderController controller = loader.getController();
                    Airport airport = AppController.AIRPORTS.get(this.getTableRow().getItem().getHomeAirportCode());
                    Optional<Flight> activeFlight = airport.getActiveFlight(this.getTableRow().getItem());
                    if (activeFlight.isPresent()) {
                        Stage stage = new Stage();
                        stage.setTitle("Edit Passenger/Employee List");
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initStyle(StageStyle.UTILITY);
                        stage.setScene(new Scene(parent));
                        controller.setFlight(activeFlight.get(), !this.getTableRow().getItem().hasPilots());
                        controller.setUpdateTable(aVoid -> this.getTableView().refresh());
                        stage.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.getDialogPane().setStyle("-fx-base: rgb(34,34,34);");
                        alert.setTitle("Active Flight");
                        alert.setHeaderText("No Flight found.");
                        alert.setContentText("Please set the destination airport code.");
                        alert.getButtonTypes().setAll(ButtonType.OK);
                        alert.showAndWait();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            box.getChildren().setAll(label, edit);
            super.setGraphic(box);
        } else {
            super.setGraphic(null);
        }
    }

    @Override
    public TableCell<Airplane, String> call(TableColumn<Airplane, String> column) {
        return new PassCountCellFactory();
    }
}
