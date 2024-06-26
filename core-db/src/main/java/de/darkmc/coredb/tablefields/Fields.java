package de.darkmc.coredb.tablefields;

public enum Fields
{
    VARCHAR("varchar", VarcharField.class),
    INTEGER("int", IntField.class),
    TIMESTAMP("timestamp", TimestampField.class),
    DATE("date", DateField.class),
    LONG("bigint", BigIntField.class),
    DATETIME("datetime", DateField.class);

    private final String name;
    private final Class<? extends TableField<?>> field;

    Fields(String name, Class<? extends TableField<?>> field)
    {
        this.name = name;
        this.field = field;
    }

    public static Class<? extends TableField<?>> getByName(String name)
    {
        for (Fields field : values()) {
            if (field.name.equals(name.toLowerCase()))
                return field.field;
        }

        return null;
    }
}
