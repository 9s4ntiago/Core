package gg.invalid.db.column;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooleanColumn extends Column<Boolean> {
    public BooleanColumn(String name, Boolean value) {
        super(name, value);
    }

    @Override
    public void setValue(PreparedStatement statement, int index) throws SQLException {
        statement.setBoolean(index, value);
    }

    @Override
    public Boolean getValue(ResultSet resultSet) throws SQLException {
        return resultSet.getBoolean(name);
    }
}
