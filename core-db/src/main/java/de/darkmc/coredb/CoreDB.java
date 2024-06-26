package de.darkmc.coredb;

import java.util.logging.Logger;

public class CoreDB
{
    private static Logger DEFAULT_LOGGER;

    public static void setDefaultLogger(Logger defaultLogger)
    {
        DEFAULT_LOGGER = defaultLogger;
    }

    public static Logger getDefaultLogger()
    {
        return DEFAULT_LOGGER;
    }
}
