package org.sql.impl;

import org.people.impl.Passenger;
import org.sql.SQLCallback;
import org.sql.SQLManager;

import java.sql.Connection;
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
    public List<Passenger> call() throws SQLException {
        List<Passenger> passengers = new ArrayList<>();
        Connection con = SQLManager.getSqlManager().createConnection();
        try {
            int pid = Integer.parseInt(searchText);
            ResultSet resultSet = con.prepareStatement("SELECT * FROM Passenger WHERE pid = " + pid + ";").executeQuery();
            addPass(passengers, resultSet);
        } catch (NumberFormatException e) {
            ResultSet resultSet = con.prepareStatement("SELECT * FROM Passenger WHERE LOWER(CONCAT(IFNULL(name, ''), IFNULL(address, ''), IFNULL(telno, ''))) LIKE LOWER(%" + searchText + "%);").executeQuery();
            addPass(passengers, resultSet);
        }
        con.close();
        return passengers;
    }

    private void addPass(List<Passenger> passengers, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int pid = resultSet.getInt("pid");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("telno");
            String[] nameData = name.split(" ");
            passengers.add(new Passenger(nameData[0], nameData[nameData.length - 1], pid, address, phone));
        }
    }
}
