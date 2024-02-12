package com.systemmanager.database.service.domains;

public interface IDefaultDBService {

    boolean loadDefaultDataIntoDatabase();
    boolean deleteAllData();
    boolean deleteAllDefaultData();
    boolean existTable();
    boolean deleteTable();
    boolean createTable();

}
