
package com.quartercode.qcutil.net.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.quartercode.qcutil.utility.ObjectUtil;

public abstract class SQLConnection {

    protected Connection connection;
    protected String     database;
    protected String     table;

    protected String     dbHost;
    protected String     dbPort;
    protected String     dbUser;
    protected String     dbPassword;

    protected SQLConnection(String host, String port, String username, String password) {

        super();

        dbHost = host;
        dbPort = port;
        dbUser = username;
        dbPassword = password;
    }

    public void selectDatabase(String database) throws ClassNotFoundException, SQLException {

        this.database = database;

        connectToDatabase(database);
    }

    protected abstract void connectToDatabase(String database) throws ClassNotFoundException, SQLException;

    public void disconnect() throws SQLException {

        connection.close();
        connection = null;
    }

    public Connection getConnection() {

        return connection;
    }

    public boolean isConnectedToDatabase() {

        return connection != null;
    }

    public void selectTable(String table) {

        this.table = table;
    }

    public String getCurrentTable() {

        return table;
    }

    public ResultSet executeQuery(String sqlQuery) throws SQLException {

        if (connection != null) {
            Statement query;
            query = connection.createStatement();
            if (sqlQuery.contains("SELECT")) {
                ResultSet resultSet = query.executeQuery(sqlQuery);
                return resultSet;
            } else {
                query.executeUpdate(sqlQuery);
                return null;
            }
        } else {
            return null;
        }
    }

    protected String handleValue(Object value) {

        if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return String.valueOf(value);
        }
    }

    public boolean exists(String col, Object value) {

        try {
            getCell(col, col, value);
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    public ResultSet get(String col) throws SQLException {

        ResultSet resultSet = executeQuery("SELECT " + col + " FROM " + table);
        return resultSet;
    }

    public ResultSet getWhere(String col, String whereClause) throws SQLException {

        ResultSet resultSet = executeQuery("SELECT " + col + " FROM " + table + " WHERE " + whereClause);
        return resultSet;
    }

    public ResultSet getWhereValue(String col, String whereCol, Object whereValue) throws SQLException {

        ResultSet resultSet = executeQuery("SELECT " + col + " FROM " + table + " WHERE " + whereCol + " = " + handleValue(whereValue));
        return resultSet;
    }

    public Object getCell(String col, String whereClause) throws SQLException {

        ResultSet resultSet = getWhere(col, whereClause);
        resultSet.next();
        return resultSet.getObject(col);
    }

    public Object getCell(String col, String whereCol, Object whereValue) throws SQLException {

        ResultSet resultSet = getWhereValue(col, whereCol, whereValue);
        resultSet.next();
        return resultSet.getObject(col);
    }

    public void set(String col, Object value) throws SQLException {

        executeQuery("UPDATE " + table + " SET " + col + " = " + handleValue(value));
    }

    public void set(String col, String whereClause, Object value) throws SQLException {

        executeQuery("UPDATE " + table + " SET " + col + " = " + handleValue(value) + " WHERE " + whereClause);
    }

    public void set(String col, String whereCol, Object whereValue, Object value) throws SQLException {

        executeQuery("UPDATE " + table + " SET " + col + " = " + handleValue(value) + " WHERE " + whereCol + " = " + handleValue(whereValue));
    }

    public void insert(Object[] values) throws SQLException {

        String valuesString = "";
        for (int counter = 0; counter < values.length; counter++) {
            valuesString += handleValue(values[counter]);
            if (counter < values.length - 1) {
                valuesString += ", ";
            }
        }

        executeQuery("INSERT INTO " + table + " VALUES (" + valuesString + ")");
    }

    public void insert(String[] cols, Object[] values) throws SQLException {

        if (cols.length != values.length) {
            return;
        }

        String colsString = "";
        for (int counter = 0; counter < cols.length; counter++) {
            colsString += cols[counter];
            if (counter < cols.length - 1) {
                colsString += ", ";
            }
        }

        String valuesString = "";
        for (int counter = 0; counter < values.length; counter++) {
            valuesString += handleValue(values[counter]);
            if (counter < values.length - 1) {
                valuesString += ", ";
            }
        }

        executeQuery("INSERT INTO " + table + " (" + colsString + ") VALUES (" + valuesString + ")");
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (connection == null ? 0 : connection.hashCode());
        result = prime * result + (database == null ? 0 : database.hashCode());
        result = prime * result + (dbHost == null ? 0 : dbHost.hashCode());
        result = prime * result + (dbPassword == null ? 0 : dbPassword.hashCode());
        result = prime * result + (dbPort == null ? 0 : dbPort.hashCode());
        result = prime * result + (dbUser == null ? 0 : dbUser.hashCode());
        result = prime * result + (table == null ? 0 : table.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SQLConnection other = (SQLConnection) obj;
        if (connection == null) {
            if (other.connection != null) {
                return false;
            }
        } else if (!connection.equals(other.connection)) {
            return false;
        }
        if (database == null) {
            if (other.database != null) {
                return false;
            }
        } else if (!database.equals(other.database)) {
            return false;
        }
        if (dbHost == null) {
            if (other.dbHost != null) {
                return false;
            }
        } else if (!dbHost.equals(other.dbHost)) {
            return false;
        }
        if (dbPassword == null) {
            if (other.dbPassword != null) {
                return false;
            }
        } else if (!dbPassword.equals(other.dbPassword)) {
            return false;
        }
        if (dbPort == null) {
            if (other.dbPort != null) {
                return false;
            }
        } else if (!dbPort.equals(other.dbPort)) {
            return false;
        }
        if (dbUser == null) {
            if (other.dbUser != null) {
                return false;
            }
        } else if (!dbUser.equals(other.dbUser)) {
            return false;
        }
        if (table == null) {
            if (other.table != null) {
                return false;
            }
        } else if (!table.equals(other.table)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectStringWithNames(this, "dbHost", "dbPort", "dbUser", "dbPassword", "database", "table");
    }

}
