package de.darkmc.corespigot.account;

import de.darkmc.corecommon.ChatColor;
import de.darkmc.corecommon.PlayerRank;
import de.darkmc.corespigot.CoreSpigot;
import de.darkmc.corespigot.Module;
import de.darkmc.corespigot.repository.AccountRepository;
import de.darkmc.coreutils.Callback;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AccountManager extends Module
{
    private final AccountRepository repository;
    private final Map<UUID, PlayerAccount> accounts = new ConcurrentHashMap<>();

    public AccountManager(CoreSpigot plugin)
    {
        super(plugin);
        repository = new AccountRepository(plugin);
    }

    public void loadAccount(PlayerAccount account)
    {
        Callback<ResultSet> callback = (result) -> {
            try {
                if (result.next()) {
                    System.out.println(result.getInt(1));
                    System.out.println(result.getString(2));

                    /*System.out.println(result.getString(2));
                    System.out.println(result.getInt(2));
                    System.out.println(result.getDate(1));
                    System.out.println(result.getDate(2));
                    System.out.println(result.getDate(3));
                    System.out.println(result.getString(3));
                    System.out.println(result.getString(4));*/

                    /*account.setPlayerId(result.getInt(1));
                    account.setGems(result.getInt("gems"));
                    account.setFirstLogin(result.getDate("firstLogin"));
                    account.setLastLogin(result.getDate("lastLogin"));
                    account.setLastLogout(result.getDate("lastLogout"));
                    account.setRank(PlayerRank.valueOf(result.getString("rank")));
                    account.setPackageRank(PlayerRank.valueOf(result.getString("packageRank")));*/
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        account.setPlayerId(repository.accountId(account.getUUID().toString()));
        if (account.getPlayerId() == -1) {
            repository.registerNewAccount(account.getUUID().toString(), account.getName(), callback);
        }
        else {
            repository.fetchAccountData(account.getUUID().toString(), callback);
        }
    }

    @EventHandler
    public void playerLogin(PlayerLoginEvent event)
    {
        Player player = event.getPlayer();
        try {
            PlayerAccount account = add(player);
            runAsync(() -> loadAccount(account));
        }
        catch (Exception exception) {
            plugin.getLogger().severe("No se pudo cargar la información de " + player.getName() + ". " + exception);
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "No se pudo cargar tú información. Intenta de nuevo en un momento.");
        }
    }

    private PlayerAccount add(Player player)
    {
        PlayerAccount account;
        if ((account = accounts.get(player.getUniqueId())) != null) {
            return account;
        }
        accounts.put(player.getUniqueId(), new PlayerAccount(player.getUniqueId(), player.getName()));
        return add(player);
    }
}
