package de.darkmc.corespigot.account;

import de.darkmc.corecommon.PlayerRank;

import java.sql.Date;
import java.util.UUID;

public class PlayerAccount
{
    private final UUID uuid;
    private final String name;
    private int playerId;
    private int gems;
    private Date firstLogin;
    private Date lastLogin;
    private Date lastLogout;
    private PlayerRank rank;
    private PlayerRank packageRank;

    public PlayerAccount(UUID uuid, String name)
    {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUUID()
    {
        return uuid;
    }

    public String getName()
    {
        return name;
    }

    public int getPlayerId()
    {
        return playerId;
    }

    protected void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }

    public int getGems()
    {
        return gems;
    }

    public void setGems(int gems)
    {
        this.gems = gems;
    }

    public Date getFirstLogin()
    {
        return firstLogin;
    }

    public void setFirstLogin(Date firstLogin)
    {
        this.firstLogin = firstLogin;
    }

    public Date getLastLogin()
    {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin)
    {
        this.lastLogin = lastLogin;
    }

    public Date getLastLogout()
    {
        return lastLogout;
    }

    public void setLastLogout(Date lastLogout)
    {
        this.lastLogout = lastLogout;
    }

    public PlayerRank getRank()
    {
        return rank;
    }

    public void setRank(PlayerRank rank)
    {
        this.rank = rank;
    }

    public PlayerRank getPackageRank()
    {
        return packageRank;
    }

    public void setPackageRank(PlayerRank packageRank)
    {
        this.packageRank = packageRank;
    }
}
