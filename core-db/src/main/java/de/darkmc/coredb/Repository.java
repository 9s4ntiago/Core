package de.darkmc.coredb;

import de.darkmc.coredb.column.Column;
import de.darkmc.coreutils.Callback;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public abstract class Repository
{
    private final BasicDataSource dataSource;

    public Repository(BasicDataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public BasicDataSource getDataSource()
    {
        return dataSource;
    }

    public Connection getConnection()
    {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        }
        catch (SQLException ignored) {
        }
        return conn;
    }

    public void executeUpdate(String query, Callback<ResultSet> callback, Column<?>... columns)
    {
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            for (int i = 0; i < columns.length; i++) {
                columns[i].setValue(statement, i + 1);
            }

            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (callback != null)
                    callback.callback(resultSet);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeUpdate(String query, Column<?>... columns)
    {
        executeUpdate(query, null, columns);
    }

    public void executeQuery(String query, Callback<ResultSet> callback, Column<?>... columns)
    {
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            for (int i = 0; i < columns.length; i++) {
                columns[i].setValue(statement, i + 1);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                if (callback != null)
                    callback.callback(resultSet);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeQuery(String query, Column<?>... columns)
    {
        executeQuery(query, null, columns);
    }
}
