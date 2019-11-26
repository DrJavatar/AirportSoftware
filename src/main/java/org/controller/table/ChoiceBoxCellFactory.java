package org.controller.table;

import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.airport.Airplane;
import org.airport.Airport;
import org.controller.AppController;

public class ChoiceBoxCellFactory extends TableCell<Airplane, String> implements Callback<TableColumn<Airplane, String>, TableCell<Airplane, String>> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        super.setText(null);
        if (!empty && item != null) {
            HBox box = new HBox();
            box.setStyle("-fx-base: rgb(34,34,34)");
            ChoiceBox<Airport> airports = new ChoiceBox<>();
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
            box.setAlignment(Pos.CENTER);
            box.getChildren().setAll(airports);
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
