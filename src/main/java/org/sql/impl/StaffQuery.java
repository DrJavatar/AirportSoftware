package org.sql.impl;

import org.people.EmployeeRole;
import org.people.impl.Employee;
import org.sql.SQLCallback;
import org.sql.SQLManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffQuery implements SQLCallback<Employee> {
    @Override
    public List<Employee> call() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        Connection con = SQLManager.getSqlManager().createConnection();

        ResultSet resultSet = con.prepareStatement("SELECT * FROM Staff;").executeQuery();

        while (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String role = resultSet.getString("role");
            int id = resultSet.getInt("id");
            employees.add(new Employee(firstName, lastName, id, EmployeeRole.valueOf(role.toUpperCase())));
        }

        con.close();
        return employees;
    }
}
