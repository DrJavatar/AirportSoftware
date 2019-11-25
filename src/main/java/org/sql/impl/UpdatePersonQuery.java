package org.sql.impl;

import org.people.Person;
import org.people.impl.Employee;
import org.people.impl.Passenger;
import org.sql.SQLCallback;
import org.sql.SQLManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpdatePersonQuery implements SQLCallback<Person> {

    private Person[] people;

    public UpdatePersonQuery(Person... people) {
        this.people = people;
    }

    @Override
    public List<Person> call() throws SQLException {
        List<Person> persons = new ArrayList<>();
        Connection con = SQLManager.getSqlManager().createConnection();

        for(Person p : people) {
            if(p instanceof Employee) {
                PreparedStatement stat = con.prepareStatement("INSERT INTO Staff (id, firstName, lastName, role) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE firstName = ?, lastName = ?, role = ?;");
                stat.setInt(1, p.getSocialNumber());
                stat.setString(2, p.getFirstName());
                stat.setString(3, p.getLastName());
                stat.setString(4, ((Employee) p).getRole().name().toLowerCase());
                stat.setString(5, p.getFirstName());
                stat.setString(6, p.getLastName());
                stat.setString(7, ((Employee) p).getRole().name().toLowerCase());
                stat.executeUpdate();
            } else if(p instanceof Passenger) {
                PreparedStatement stat = con.prepareStatement("INSERT INTO Passenger (pid, name, address, telno) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE name = ?, address = ?, telno = ?;");
                stat.setInt(1, p.getSocialNumber());
                String name = p.getFirstName() + " " + p.getLastName();
                stat.setString(2, name);
                stat.setString(3, ((Passenger) p).getAddress());
                stat.setString(4, ((Passenger) p).getPhone());
                stat.setString(5, name);
                stat.setString(6, ((Passenger) p).getAddress());
                stat.setString(7, ((Passenger) p).getPhone());
                stat.executeUpdate();
            }
        }

        con.close();
        return persons;
    }
}
