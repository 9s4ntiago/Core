package de.darkmc.corespigot;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public abstract class Module implements Listener
{
    protected final CoreSpigot plugin;

    public Module(CoreSpigot plugin)
    {
        this.plugin = plugin;
    }

    public void registerSelfListener()
    {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
    }
}
