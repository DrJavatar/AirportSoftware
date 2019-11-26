package org.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SQLManager {

    private static final SQLManager sqlManager = new SQLManager();

    public static SQLManager getSqlManager() {
        return sqlManager;
    }

    private SQLManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection createConnection() throws SQLException {
        SQLConnectionConfig config = SQLConnectionConfig.SQL_CONNECTION_CONFIG;
        return DriverManager.getConnection("jdbc:mysql://" + config.getHost() + ":" + SQLConnectionConfig.SQL_CONNECTION_CONFIG.getPort() + "/airpots", config.getUser(), config.getPass());
    }
}
