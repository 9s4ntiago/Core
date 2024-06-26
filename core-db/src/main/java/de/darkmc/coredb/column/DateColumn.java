package de.darkmc.coredb.column;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DateColumn extends Column<Date> {
    public DateColumn(String name, Date value) {
        super(name, value);
    }

    @Override
    public void setValue(PreparedStatement statement, int index) throws SQLException {
        statement.setDate(index, value);
    }

    @Override
    public Date getValue(ResultSet resultSet) throws SQLException {
        return resultSet.getDate(name);
    }
}
