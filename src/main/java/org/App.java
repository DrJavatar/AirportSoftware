package org;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox parent = FXMLLoader.load(Objects.requireNonNull(App.class.getClassLoader().getResource("fxml/Airport.fxml")));
        stage.setTitle("Airport Reservation Software");
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
