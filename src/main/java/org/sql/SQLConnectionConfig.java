package org.sql;

public class SQLConnectionConfig {

    public static final SQLConnectionConfig SQL_CONNECTION_CONFIG = new SQLConnectionConfig("127.0.0.1", "root", "Super130376!", 3306);

    private String host;
    private String user;
    private String pass;
    private int port;

    public SQLConnectionConfig(String host, String user, String pass, int port) {
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.port = port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public int getPort() {
        return port;
    }
}
