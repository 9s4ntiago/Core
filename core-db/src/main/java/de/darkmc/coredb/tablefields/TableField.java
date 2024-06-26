package de.darkmc.coredb.tablefields;

public interface TableField<T>
{
    Class<T> getPrimitiveType();

    String getName();
}
