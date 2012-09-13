
package com.quartercode.qcutil.net.sql;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection extends SQLConnection {

    public MySQLConnection(String host, String port, String username, String password) {

        super(host, port, username, password);
    }

    @Override
    public void connectToDatabase(String database) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + database;
        connection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
    }

}
