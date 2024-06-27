package de.darkmc.corebungee;

import net.md_5.bungee.api.plugin.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class CoreBungee extends Plugin
{
    @Override
    public void onEnable()
    {
        Connection conn = null;
        try  {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS darkmc;");
                stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS darkmc_account;");
            }
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
