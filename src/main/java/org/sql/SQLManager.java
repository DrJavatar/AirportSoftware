package org.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLManager {

    private static final SQLManager sqlManager = new SQLManager();

    public static SQLManager getSqlManager() {
        return sqlManager;
    }

    private Connection connection;

    private SQLManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/airpots", "root", "Super130376!");
    }
}
