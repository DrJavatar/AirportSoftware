package org.sql.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.people.impl.Passenger;
import org.sql.SQLCallback;
import org.sql.SQLManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerSearchQuery implements SQLCallback<Passenger> {

    private String searchText;

    public PassengerSearchQuery(String searchText) {
        this.searchText = searchText;
    }

    @Override
    public ObservableList<Passenger> call() throws SQLException {
        List<Passenger> passengers = new ArrayList<>();
        Connection con = SQLManager.getSqlManager().createConnection();
        try {
            int pid = Integer.parseInt(searchText);
            ResultSet resultSet = con.prepareStatement("SELECT * FROM Passenger WHERE pid = " + pid + ";").executeQuery();
            addPass(passengers, resultSet);
        } catch (NumberFormatException e) {
            PreparedStatement stat = con.prepareStatement("SELECT * FROM Passenger WHERE LOWER(CONCAT(IFNULL(name, ''), IFNULL(address, ''), IFNULL(telno, ''), IFNULL(flight_no, ''))) LIKE LOWER(?);");
            stat.setString(1, "%" + searchText + "%");
            ResultSet resultSet = stat.executeQuery();
            addPass(passengers, resultSet);
        }
        con.close();
        return FXCollections.observableArrayList(passengers);
    }

    private void addPass(List<Passenger> passengers, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int pid = resultSet.getInt("pid");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("telno");
            int flight_no = resultSet.getInt("flight_no");
            String[] nameData = name.split(" ");
            Passenger passenger = new Passenger(nameData[0], nameData[nameData.length - 1], pid, address, phone);
            passenger.setFlightNumber(flight_no);
            passengers.add(passenger);
        }
    }
}
