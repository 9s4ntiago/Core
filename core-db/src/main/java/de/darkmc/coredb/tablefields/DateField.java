package de.darkmc.coredb.tablefields;

import java.sql.Timestamp;

public class TimestampField implements TableField<Timestamp>
{
    private final String name;

    public TimestampField(String name)
    {
        this.name = name;
    }

    @Override
    public Class<Timestamp> getPrimitiveType()
    {
        return Timestamp.class;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
