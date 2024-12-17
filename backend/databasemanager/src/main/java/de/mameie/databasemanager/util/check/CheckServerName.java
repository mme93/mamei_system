package de.mameie.databasemanager.util.check;

import de.mameie.databasemanager.sql.server.connection.DBServerSettings;
import de.mameie.databasemanager.util.check.exception.ServerNameNotFoundException;

public class CheckServerName {

    public static boolean exist(String serverName){
        if( DBServerSettings.SERVER_NAMES.contains(serverName)){
            return true;
        }
        throw new ServerNameNotFoundException(serverName);
    }
}
