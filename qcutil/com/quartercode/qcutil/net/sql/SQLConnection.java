
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

    protected SQLConnection(final String host, final String port, final String username, final String password) {

        super();

        dbHost = host;
        dbPort = port;
        dbUser = username;
        dbPassword = password;
    }

    public void selectDatabase(final String database) throws ClassNotFoundException, SQLException {

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

    public void selectTable(final String table) {

        this.table = table;
    }

    public String getCurrentTable() {

        return table;
    }

    public ResultSet executeQuery(final String sqlQuery) throws SQLException {

        if (connection != null) {
            Statement query;
            query = connection.createStatement();
            if (sqlQuery.contains("SELECT")) {
                final ResultSet resultSet = query.executeQuery(sqlQuery);
                return resultSet;
            } else {
                query.executeUpdate(sqlQuery);
                return null;
            }
        } else {
            return null;
        }
    }

    protected String handleValue(final Object value) {

        if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return String.valueOf(value);
        }
    }

    public boolean exists(final String col, final Object value) {

        try {
            getCell(col, col, value);
            return true;
        }
        catch (final SQLException e) {
            return false;
        }
    }

    public ResultSet get(final String col) throws SQLException {

        final ResultSet resultSet = executeQuery("SELECT " + col + " FROM " + table);
        return resultSet;
    }

    public ResultSet getWhere(final String col, final String whereClause) throws SQLException {

        final ResultSet resultSet = executeQuery("SELECT " + col + " FROM " + table + " WHERE " + whereClause);
        return resultSet;
    }

    public ResultSet getWhereValue(final String col, final String whereCol, final Object whereValue) throws SQLException {

        final ResultSet resultSet = executeQuery("SELECT " + col + " FROM " + table + " WHERE " + whereCol + " = " + handleValue(whereValue));
        return resultSet;
    }

    public Object getCell(final String col, final String whereClause) throws SQLException {

        final ResultSet resultSet = getWhere(col, whereClause);
        resultSet.next();
        return resultSet.getObject(col);
    }

    public Object getCell(final String col, final String whereCol, final Object whereValue) throws SQLException {

        final ResultSet resultSet = getWhereValue(col, whereCol, whereValue);
        resultSet.next();
        return resultSet.getObject(col);
    }

    public void set(final String col, final Object value) throws SQLException {

        executeQuery("UPDATE " + table + " SET " + col + " = " + handleValue(value));
    }

    public void set(final String col, final String whereClause, final Object value) throws SQLException {

        executeQuery("UPDATE " + table + " SET " + col + " = " + handleValue(value) + " WHERE " + whereClause);
    }

    public void set(final String col, final String whereCol, final Object whereValue, final Object value) throws SQLException {

        executeQuery("UPDATE " + table + " SET " + col + " = " + handleValue(value) + " WHERE " + whereCol + " = " + handleValue(whereValue));
    }

    public void insert(final Object[] values) throws SQLException {

        String valuesString = "";
        for (int counter = 0; counter < values.length; counter++) {
            valuesString += handleValue(values[counter]);
            if (counter < values.length - 1) {
                valuesString += ", ";
            }
        }

        executeQuery("INSERT INTO " + table + " VALUES (" + valuesString + ")");
    }

    public void insert(final String[] cols, final Object[] values) throws SQLException {

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
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SQLConnection other = (SQLConnection) obj;
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
