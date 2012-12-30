
package com.quartercode.qcutil.net.sql;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection extends SQLConnection {

    public MySQLConnection(final String host, final String port, final String username, final String password) {

        super(host, port, username, password);
    }

    @Override
    public void connectToDatabase(final String database) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        final String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + database;
        connection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
    }

}
