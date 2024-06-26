package de.darkmc.coredb.tablefields;

public class IntField implements TableField<Integer>
{
    private final String name;

    public IntField(String name)
    {
        this.name = name;
    }

    @Override
    public Class<Integer> getPrimitiveType()
    {
        return int.class;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
