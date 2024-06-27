package de.darkmc.coredb;

import de.darkmc.coredb.tablefields.TableField;

import java.sql.Connection;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class SQLContext
{
    private final Connection connection;
    private final Table usingTable;

    public SQLContext(Connection connection, Table usingTable)
    {
        this.connection = connection;
        this.usingTable = usingTable;
    }

    public void select(TableField<?>... fields)
    {
        String str = Arrays.stream(fields).map(TableField::getName)
                .collect(Collectors.joining(", "));


    }
}
