package com.systemmanager.database.assets;

import java.util.List;

import static java.util.Arrays.asList;

public class DatabaseTableNames {

    public static final String EMPTY = "EMPTY";


    public static final String PROCESS = "process";
    public static final String ACCOUNT = "account";
    public static final String ACCOUNT_SEQ = "account_seq";
    public static final String DEPENDED_PROCESS = "depended_process";
    public static final String MICRO_SERVICE = "micro_service";
    public static final String PRIVILEGES = "privileges";
    public static final String PRIVILEGES_SEQ = "privileges_seq";
    public static final String SECURITY_USER = "security_user";
    public static final String SUDOKU = "sudoku";
    public static final String SUDOKU_LEVEL_LIST_ITEM = "sudoku_level_list_item";
    public static final String TASK = "task";

    public static final List<String> tableNameList = asList(PROCESS, ACCOUNT, ACCOUNT_SEQ, DEPENDED_PROCESS,
            MICRO_SERVICE, PRIVILEGES, PRIVILEGES_SEQ, SECURITY_USER, SUDOKU, SUDOKU_LEVEL_LIST_ITEM, TASK);

    public static final String tableNames = "account, account_seq, depended_process, micro_service, privileges, " +
            "privileges_seq, process, security_user, sudoku, sudoku_level_list_item, task";

    public static final  List<String> apiTableNameList= asList(PROCESS);

    public static final List<String> defaultTableNameList = asList(PROCESS);

}










