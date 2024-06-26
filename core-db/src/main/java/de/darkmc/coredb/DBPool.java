package de.darkmc.coredb;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBPool
{
    private static final String SQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String OLD_SQL_DRIVER = "com.mysql.jdbc.Driver";
    public static final BasicDataSource DARK_MC = openDataSource("jdbc:mysql://localhost:3306/darkmc", "root", "");
    public static final BasicDataSource ACCOUNT = openDataSource("jdbc:mysql://localhost:3306/darkmc_account", "root", "");

    private static BasicDataSource openDataSource(String url, String user, String pw)
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.addConnectionProperty("allowMultiQueries", "true");
        dataSource.addConnectionProperty("useSSL", "true");
        dataSource.addConnectionProperty("autoReconnect", "true");
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(pw);
        boolean USING_NEW_SQL_DRIVER;
        try {
            Class.forName(SQL_DRIVER);
            USING_NEW_SQL_DRIVER = true;
        }
        catch (ClassNotFoundException exception) {
            try {
                Class.forName(OLD_SQL_DRIVER);
                USING_NEW_SQL_DRIVER = false;
            }
            catch (ClassNotFoundException exception1) {
                CoreDB.getDefaultLogger().severe("No se encontró un Driver para MySQL lol.");
                throw new RuntimeException(exception1);
            }
        }
        dataSource.setDriverClassName(USING_NEW_SQL_DRIVER ? SQL_DRIVER : OLD_SQL_DRIVER);
        return dataSource;
    }
}
