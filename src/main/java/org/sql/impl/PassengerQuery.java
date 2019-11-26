package org.sql.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.people.impl.Passenger;
import org.sql.SQLCallback;
import org.sql.SQLManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerQuery implements SQLCallback<Passenger> {
    @Override
    public ObservableList<Passenger> call() throws SQLException {
        List<Passenger> passengers = new ArrayList<>();
        Connection con = SQLManager.getSqlManager().createConnection();

        ResultSet resultSet = con.prepareStatement("SELECT * FROM Passenger;").executeQuery();

        while (resultSet.next()) {
            int pid = resultSet.getInt("pid");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("telno");
            String[] nameData = name.split(" ");
            int flight_no = resultSet.getInt("flight_no");
            Passenger passenger = new Passenger(nameData[0], nameData[nameData.length - 1], pid, address, phone);
            passenger.setFlightNumber(flight_no);
            passengers.add(passenger);
        }

        con.close();
        return FXCollections.observableArrayList(passengers);
    }
}
