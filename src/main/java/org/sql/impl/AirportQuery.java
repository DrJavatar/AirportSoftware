package org.sql.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.airport.Airport;
import org.sql.SQLCallback;
import org.sql.SQLManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirportQuery implements SQLCallback<Airport> {
    @Override
    public ObservableList<Airport> call() throws SQLException {
        List<Airport> airports = new ArrayList<>();
        Connection con = SQLManager.getSqlManager().createConnection();

        ResultSet resultSet = con.prepareStatement("SELECT * FROM AirportCode;").executeQuery();

        while (resultSet.next()) {
            airports.add(new Airport(resultSet.getString("airport_code")));
        }

        con.close();
        return FXCollections.observableArrayList(airports);
    }
}
