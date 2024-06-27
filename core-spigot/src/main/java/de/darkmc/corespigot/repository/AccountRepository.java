package de.darkmc.corespigot.repository;

import de.darkmc.corecommon.PlayerRank;
import de.darkmc.coredb.DBPool;
import de.darkmc.coredb.Repository;
import de.darkmc.coredb.column.IntColumn;
import de.darkmc.coredb.column.VarcharColumn;
import de.darkmc.corespigot.CoreSpigot;
import de.darkmc.coreutils.Callback;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.*;

public class AccountRepository extends Repository
{
    private static final String CREATE_ACCOUNT_TABLE = "CREATE TABLE IF NOT EXISTS accounts(id INT NOT NULL AUTO_INCREMENT, name VARCHAR(100), uuid VARCHAR(100), gems INT, firstLogin DATETIME, lastLogin DATETIME, lastLogout DATETIME, rank VARCHAR(100), packageRank VARCHAR(100), PRIMARY KEY(id));";
    private static final String REGISTER_NEW_ACCOUNT = "INSERT INTO accounts(name, uuid, gems, firstLogin, lastLogin, lastLogout, rank, packageRank) VALUES (?, ?, ?, NOW(), NOW(), NOW(), ?, ?);";
    private static final String FETCH_DATA_BY_UUID = "SELECT gems, firstLogin, lastLogin, lastLogout, rank, packageRank FROM accounts WHERE uuid=?";

    public AccountRepository(CoreSpigot plugin)
    {
        super(DBPool.ACCOUNT);
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                executeUpdate(CREATE_ACCOUNT_TABLE);
            }
        }.runTaskAsynchronously(plugin);
    }

    public int accountId(String uuid)
    {
        int accountId = -1;
        try (
                Connection connection = getConnection();
                Statement stmt = connection.createStatement()
        ) {
            try (ResultSet resultSet = stmt.executeQuery("SELECT id FROM accounts WHERE uuid='" + uuid + "';")) {
                if (resultSet.next()) {
                    accountId = resultSet.getInt(1);
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountId;
    }

    public void registerNewAccount(String uuid, String playerName, Callback<ResultSet> callback)
    {
        executeUpdate(REGISTER_NEW_ACCOUNT, callback,
                new VarcharColumn("uuid", uuid),
                new VarcharColumn("name", playerName),
                new IntColumn("gems", 0),
                new VarcharColumn("rank", PlayerRank.NONE.name()),
                new VarcharColumn("packageRank", PlayerRank.NONE.name())
        );
    }

    public void fetchAccountData(String uuid, Callback<ResultSet> callback)
    {
        executeQuery(FETCH_DATA_BY_UUID, callback, new VarcharColumn("uuid", uuid));
    }
}
