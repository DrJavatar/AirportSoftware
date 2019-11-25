package org.sql.impl;

import org.people.EmployeeRole;
import org.people.impl.Employee;
import org.sql.SQLCallback;
import org.sql.SQLManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffSearchQuery implements SQLCallback<Employee> {

    private String searchText;

    public StaffSearchQuery(String searchText) {
        this.searchText = searchText;
    }

    @Override
    public List<Employee> call() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        Connection con = SQLManager.getSqlManager().createConnection();
        try {
            int pid = Integer.parseInt(searchText);
            ResultSet resultSet = con.prepareStatement("SELECT * FROM Staff WHERE id = " + pid + ";").executeQuery();
            addPass(employees, resultSet);
        } catch (NumberFormatException e) {
            PreparedStatement stat = con.prepareStatement("SELECT * FROM Staff WHERE LOWER(CONCAT(IFNULL(firstName, ''), IFNULL(lastName, ''), IFNULL(role, ''))) LIKE LOWER(?);");
            stat.setString(1, "%" + searchText + "%");
            ResultSet resultSet = stat.executeQuery();
            addPass(employees, resultSet);
        }
        con.close();
        return employees;
    }

    private void addPass(List<Employee> employees, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int pid = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String role = resultSet.getString("role");
            employees.add(new Employee(firstName, lastName, pid, EmployeeRole.valueOf(role.toUpperCase())));
        }
    }
}
