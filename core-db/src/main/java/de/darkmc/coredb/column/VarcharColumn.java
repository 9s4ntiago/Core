package gg.invalid.db.column;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VarcharColumn extends Column<String>{
    public VarcharColumn(String name, String value) {
        super(name, value);
    }

    @Override
    public void setValue(PreparedStatement statement, int index) throws SQLException {
        statement.setString(index, value);
    }

    @Override
    public String getValue(ResultSet resultSet) throws SQLException {
        return resultSet.getString(name);
    }
}
