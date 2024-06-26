package de.darkmc.coredb.tablefields;

public class VarcharField implements TableField<String>
{
    private final String name;

    public VarcharField(String name)
    {
        this.name = name;
    }

    @Override
    public Class<String> getPrimitiveType()
    {
        return String.class;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
