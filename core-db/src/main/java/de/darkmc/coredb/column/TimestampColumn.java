package gg.invalid.db.column;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TimestampColumn extends Column<Timestamp> {
    public TimestampColumn(String name, Timestamp value) {
        super(name, value);
    }

    @Override
    public void setValue(PreparedStatement statement, int index) throws SQLException {
        statement.setTimestamp(index, value);
    }

    @Override
    public Timestamp getValue(ResultSet resultSet) throws SQLException {
        return resultSet.getTimestamp(name);
    }
}
