package de.darkmc.coredb.tablefields;

public class BigIntField implements TableField<Long>
{
    private final String name;

    public BigIntField(String name)
    {
        this.name = name;
    }

    @Override
    public Class<Long> getPrimitiveType()
    {
        return long.class;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
