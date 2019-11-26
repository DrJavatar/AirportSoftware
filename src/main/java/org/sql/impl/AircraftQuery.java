package org.sql.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.airport.Airplane;
import org.sql.SQLCallback;
import org.sql.SQLManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AircraftQuery implements SQLCallback<Airplane> {
    @Override
    public ObservableList<Airplane> call() throws SQLException {
        List<Airplane> planes = new ArrayList<>();
        Connection con = SQLManager.getSqlManager().createConnection();

        ResultSet set = con.prepareStatement("SELECT * FROM Aircraft;").executeQuery();

        while(set.next()) {
            String aircrafType = set.getString("aircrafttype");
            String description = set.getString("adescription");
            String homeAirportCode = set.getString("home_airport_code");
            planes.add(new Airplane(aircrafType, description, homeAirportCode));
        }

        con.close();
        return FXCollections.observableArrayList(planes);
    }
}
