package de.darkmc.coredb.table;

import de.darkmc.coredb.DBPool;
import de.darkmc.coredb.Table;
import de.darkmc.coredb.tablefields.IntField;
import de.darkmc.coredb.tablefields.VarcharField;

public class TableAccounts extends Table
{
    protected TableAccounts()
    {
        super("accounts", DBPool.ACCOUNT);
    }

    public final IntField id = (IntField) getField("id");
    public final VarcharField name = (VarcharField) getField("name");
    public final VarcharField uuid = (VarcharField) getField("uuid");
    public final IntField gems = (IntField) getField("gems");
}
