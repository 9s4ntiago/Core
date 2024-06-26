package de.darkmc.coredb;

import de.darkmc.coredb.tablefields.Fields;
import de.darkmc.coredb.tablefields.TableField;
import org.apache.commons.dbcp2.BasicDataSource;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Table
{
    private static final String GET_ALL_COLUMNS = "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME=?;";
    private final String tableName;
    private final BasicDataSource dataSource;

    private final Map<String, String> fields = new HashMap<>();

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
            try (PreparedStatement stmt = connection.prepareStatement(GET_ALL_COLUMNS)) {
                stmt.setString(1, tableName);

                try (ResultSet resultSet = stmt.executeQuery()) {
                    while (resultSet.next()) {
                        String fieldName = resultSet.getString("COLUMN_NAME");
                        String fieldType = resultSet.getString("DATA_TYPE");
                        fields.put(fieldName, fieldType);
                    }
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

        if (!fields.containsKey(fieldName)) {
            return null;
        }

        String fieldType = fields.get(fieldName);
        Class<? extends TableField<?>> fieldClass = Fields.getByName(fieldType);
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
