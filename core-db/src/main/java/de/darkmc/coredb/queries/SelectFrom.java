package de.darkmc.coredb.queries;

import de.darkmc.coredb.Table;
import de.darkmc.coredb.tablefields.TableField;

public class SelectFrom
{
    private final StringBuilder instruction;

    public SelectFrom()
    {
        instruction = new StringBuilder("SELECT ");
    }

    public SelectFrom select(TableField<?>... fields)
    {
        String[] fieldNames = new String[fields.length];
        int index = 0;
        for (TableField<?> field : fields) {
            fieldNames[index] = field.getName();
            index++;
        }
        String str = String.join(", ", fieldNames);
        instruction.append(str);
        return this;
    }

    public SelectFrom select()
    {
        instruction.append("*");
        return this;
    }

    public SelectFrom from(Table table)
    {
        instruction.append(" FROM ").append(table.getTableName());
        return this;
    }

    public SelectFrom where(TableField<?> field, Object value)
    {
        Class<?> primitiveType = field.getPrimitiveType();
        if (!primitiveType.isInstance(value)) {
            throw new RuntimeException();
        }

        instruction.append(" WHERE ").append(field.getName());
    }
}
