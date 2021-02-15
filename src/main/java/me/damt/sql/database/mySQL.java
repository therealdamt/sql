package me.damt.sql.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mySQL {

    private String host ;
    private String port;
    private String database;
    private String username ;
    private String password;
    private Connection connection;


    public mySQL() {
        this.host = "localhost";
        this.port = "3306";
        this.database = "test";
        this.username = "root";
        this.password = "";
    }

    public boolean isConnected() {
        return (connection != null);
    }

    public void connect() throws SQLException {
        if (!isConnected()) {
            this.connection = DriverManager.getConnection("jdbc:mysql://" +
                            host + ":" + port + "/" + database + "?useSSL=false",
                    username, password);
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public Connection getConnection() {
        return this.connection;
    }
}
