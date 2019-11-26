package org.sql.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.people.impl.Pilot;
import org.sql.SQLCallback;
import org.sql.SQLManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PilotFilterQuery implements SQLCallback<Pilot> {

    @Override
    public ObservableList<Pilot> call() throws SQLException {
        List<Pilot> pilots = new ArrayList<>();
        Connection con = SQLManager.getSqlManager().createConnection();

        ResultSet resultSet = con.prepareStatement("SELECT * FROM Staff WHERE role = 'pilot'").executeQuery();

        while (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            int flight_no = resultSet.getInt("flight_no");
            int id = resultSet.getInt("id");
            Pilot pilot = new Pilot(firstName, lastName, id);
            pilot.setFlightNumber(flight_no);
            pilots.add(pilot);
        }

        con.close();
        return FXCollections.observableArrayList(pilots);
    }
}
