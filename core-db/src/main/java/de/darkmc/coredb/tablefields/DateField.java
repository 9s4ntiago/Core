package de.darkmc.coredb.tablefields;

import java.sql.Date;

public class DateField implements TableField<Date>
{
    private final String name;

    public DateField(String name)
    {
        this.name = name;
    }

    @Override
    public Class<Date> getPrimitiveType()
    {
        return Date.class;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
