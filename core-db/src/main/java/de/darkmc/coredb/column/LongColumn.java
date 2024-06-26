package de.darkmc.coredb.column;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LongColumn extends Column<Long>{
    public LongColumn(String name, Long value) {
        super(name, value);
    }

    @Override
    public void setValue(PreparedStatement statement, int index) throws SQLException {
        statement.setLong(index, value);
    }

    @Override
    public Long getValue(ResultSet resultSet) throws SQLException {
        return resultSet.getLong(name);
    }
}
