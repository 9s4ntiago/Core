package de.darkmc.corecommon;

public enum PlayerRank
{
    NONE("", ChatColor.GRAY);

    private final String prefix;
    private final ChatColor tabColor;

    PlayerRank(String prefix, ChatColor tabColor)
    {
        this.prefix = prefix;
        this.tabColor = tabColor;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public ChatColor getTabColor()
    {
        return tabColor;
    }
}
