package de.mameie.databasemanager.util.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StaticPropertyHolder {

    private static String CLOUD_SERVER_MARIA_DB_IP;

    @Value("${cloud_server_maria_db.ip}")
    public void setStaticNameForCloudServerIp(String name) {
        CLOUD_SERVER_MARIA_DB_IP = name;
    }

    public static String getStaticNameForCloudServerIp() {
        return CLOUD_SERVER_MARIA_DB_IP;
    }

    private static String CLOUD_SERVER_MARIA_DB_USERNAME;

    @Value("${cloud_server_maria_db.user}")
    public void setStaticNameForCloudServerUserName(String name) {
        CLOUD_SERVER_MARIA_DB_USERNAME = name;
    }

    public static String getStaticNameForCloudServerUserName() {
        return CLOUD_SERVER_MARIA_DB_USERNAME;
    }

    private static String CLOUD_SERVER_MARIA_DB_PASSWORD;

    @Value("${cloud_server_maria_db.password}")
    public void setStaticNameForCloudServerPassword(String name) {
        CLOUD_SERVER_MARIA_DB_PASSWORD = name;
    }

    public static String getStaticNameForCloudServerPassword() {
        return CLOUD_SERVER_MARIA_DB_PASSWORD;
    }

    private static String CLOUD_XXL_MARIA_DB_IP;

    @Value("${cloud_xxl_maria_db.ip}")
    public void setStaticNameForCloudXxlIp(String name) {
        CLOUD_XXL_MARIA_DB_IP = name;
    }

    public static String getStaticNameForCloudXxlIp() {
        return CLOUD_XXL_MARIA_DB_IP;
    }

    private static String CLOUD_XXL_MARIA_DB_USERNAME;

    @Value("${cloud_xxl_maria_db.user}")
    public void setStaticNameForCloudXxlUserName(String name) {
        CLOUD_XXL_MARIA_DB_USERNAME = name;
    }

    public static String getStaticNameForCloudXxlUserName() {
        return CLOUD_XXL_MARIA_DB_USERNAME;
    }

    private static String CLOUD_XXL_MARIA_DB_PASSWORD;

    @Value("${cloud_xxl_maria_db.password}")
    public void setStaticNameForCloudXxlPassword(String name) {
        CLOUD_XXL_MARIA_DB_PASSWORD = name;
    }

    public static String getStaticNameForCloudXxlPassword() {
        return CLOUD_XXL_MARIA_DB_PASSWORD;
    }

}
