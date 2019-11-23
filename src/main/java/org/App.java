package org;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(App.class.getClassLoader().getResource("fxml/gui.fxml")));
        stage.setTitle("Airport Reservation Software");
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
