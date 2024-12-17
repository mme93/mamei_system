package de.mameie.databasemanager.sql.server.connection;

import java.util.List;

import static java.util.Arrays.asList;

public class DBServerSettings {

    public static String CLOUD_SERVER = "CLOUD-SERVER";
    public static String CLOUD_XXL = "CLOUD-XXL";
    public static String TEST = "TEST";
    public static List<String>SERVER_NAMES=asList(CLOUD_SERVER,CLOUD_XXL,TEST);
}
