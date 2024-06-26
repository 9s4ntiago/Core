package de.darkmc.coredb.column;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Column<T> {
    protected final String name;
    protected final T value;

    protected Column(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public abstract void setValue(PreparedStatement statement, int index) throws SQLException;

    public abstract T getValue(ResultSet resultSet) throws SQLException;
}
