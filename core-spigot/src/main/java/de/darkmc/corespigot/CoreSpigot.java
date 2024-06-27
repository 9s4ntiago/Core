package de.darkmc.corespigot;

import de.darkmc.coredb.CoreDB;
import de.darkmc.corespigot.account.AccountManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CoreSpigot extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        CoreDB.setDefaultLogger(getLogger());

        (new AccountManager(this)).registerSelfListener();
    }
}
