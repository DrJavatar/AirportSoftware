package org.sql.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.people.EmployeeRole;
import org.people.impl.Employee;
import org.sql.SQLCallback;
import org.sql.SQLManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NegatedPilotFilterQuery implements SQLCallback<Employee> {
    @Override
    public ObservableList<Employee> call() throws SQLException {
        Connection con = SQLManager.getSqlManager().createConnection();

        ResultSet resultSet = con.prepareStatement("SELECT * FROM Staff WHERE role != 'pilot'").executeQuery();
        List<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String role = resultSet.getString("role");
            int id = resultSet.getInt("id");
            int flight_no = resultSet.getInt("flight_no");
            Employee employee = new Employee(firstName, lastName, id, EmployeeRole.valueOf(role.toUpperCase().replace("-", "_")));
            employee.setFlightNumber(flight_no);
            employees.add(employee);
        }

        con.close();
        return FXCollections.observableArrayList(employees);
    }
}
