package de.darkmc.coredb;

import de.darkmc.coredb.tablefields.Fields;
import de.darkmc.coredb.tablefields.TableField;
import de.darkmc.coreutils.collection.NautSet;
import org.apache.commons.dbcp2.BasicDataSource;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Table
{
    private static final String GET_ALL_COLUMNS = "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='personas';";
    private final String tableName;
    private final BasicDataSource dataSource;

    private final NautSet<String> fields = new NautSet<>();

    public Table(String tableName, BasicDataSource dataSource)
    {
        this.tableName = tableName;
        this.dataSource = dataSource;

        loadTableFields();
    }

    public BasicDataSource getConnectionPool()
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

    private void loadTableFields()
    {
        try (Connection connection = getConnection()) {
            try (
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(GET_ALL_COLUMNS)
            ) {
                while (resultSet.next()) {
                    String fieldName = resultSet.getString("COLUMN_NAME");
                    fields.add(fieldName);
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TableField<?> getField(String fieldName)
    {
        if (fieldName == null) {
            return null;
        }

        if (fields.notContains(fieldName)) {
            return null;
        }

        Class<? extends TableField<?>> fieldClass = Fields.getByName(fieldName);
        try {
            if (fieldClass != null) {
                Constructor<?> constructor = fieldClass.getConstructor(String.class);
                return (TableField<?>) constructor.newInstance(fieldName);
            }
            return null;
        }
        catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
