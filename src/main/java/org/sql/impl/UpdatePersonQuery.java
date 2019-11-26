package org.sql.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.people.Person;
import org.people.impl.Employee;
import org.people.impl.Passenger;
import org.sql.SQLCallback;
import org.sql.SQLManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UpdatePersonQuery implements SQLCallback<Person> {

    private Person[] people;

    public UpdatePersonQuery(Person... people) {
        this.people = people;
    }

    @Override
    public ObservableList<Person> call() throws SQLException {
        List<Person> persons = new ArrayList<>();
        Connection con = SQLManager.getSqlManager().createConnection();


        for(Person p : people) {
            if(p instanceof Employee) {
                ResultSet resultSet = con.prepareStatement("SELECT COUNT(*) FROM Staff;").executeQuery();

                if(resultSet.next() && p.getSocialNumber() == -1) {
                    p.setSocialNumber(resultSet.getInt(1) + 1);
                }

                PreparedStatement stat = con.prepareStatement("INSERT INTO Staff (id, firstName, lastName, role, flight_no) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE firstName = ?, lastName = ?, role = ?, flight_no = ?;");
                stat.setInt(1, p.getSocialNumber());
                stat.setString(2, p.getFirstName());
                stat.setString(3, p.getLastName());
                stat.setString(4, ((Employee) p).getRole().name().toLowerCase());
                stat.setInt(5, p.getFlightNumber());
                stat.setString(6, p.getFirstName());
                stat.setString(7, p.getLastName());
                stat.setString(8, ((Employee) p).getRole().name().toLowerCase());
                stat.setInt(9, p.getFlightNumber());
                stat.executeUpdate();
            } else if(p instanceof Passenger) {
                ResultSet resultSet = con.prepareStatement("SELECT COUNT(*) FROM Passenger;").executeQuery();
                if(resultSet.next() && p.getSocialNumber() == -1) {
                    p.setSocialNumber(resultSet.getInt(1) + 1);
                }
                PreparedStatement stat = con.prepareStatement("INSERT INTO Passenger (pid, name, address, telno, flight_no) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE name = ?, address = ?, telno = ?, flight_no = ?;");
                stat.setInt(1, p.getSocialNumber());
                String name = p.getFirstName() + " " + p.getLastName();
                stat.setString(2, name);
                stat.setString(3, ((Passenger) p).getAddress());
                stat.setString(4, ((Passenger) p).getPhone());
                stat.setInt(5, p.getFlightNumber());
                stat.setString(6, name);
                stat.setString(7, ((Passenger) p).getAddress());
                stat.setString(8, ((Passenger) p).getPhone());
                stat.setInt(9, p.getFlightNumber());
                stat.executeUpdate();
            } else {
                System.out.println("WTF?!");
            }
        }

        con.close();
        return FXCollections.observableArrayList(persons);
    }
}
