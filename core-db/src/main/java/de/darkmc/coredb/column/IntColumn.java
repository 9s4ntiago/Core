package de.darkmc.coredb.column;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntColumn extends Column<Integer>{
    public IntColumn(String name, Integer value) {
        super(name, value);
    }

    @Override
    public void setValue(PreparedStatement statement, int index) throws SQLException {
        statement.setInt(index, value);
    }

    @Override
    public Integer getValue(ResultSet resultSet) throws SQLException {
        return resultSet.getInt(name);
    }
}
