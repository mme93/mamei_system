package com.apigateway.api.process.model.process;

import java.util.List;

import static java.util.Arrays.asList;

public class ProcessDefaultNameTable {

    /*
    Database
    */
    public final static String BACKUP_DATABASE = "BACKUP_DATABASE";
    public final static String CREATE_DATABASE = "CREATE_DATABASE";
    public final static String DELETE_DATABASE = "DELETE_DATABASE";

    /*
    Table
     */
    public final static String RESET_TO_DEFAULT_DATASET = "RESET_TO_DEFAULT_DATASET";
    public final static String RESET_ALL_TO_DEFAULT_DATASET = "RESET_ALL_TO_DEFAULT_DATASET";
    public final static String DELETE_TABLE = "DELETE_TABLE";


    /*
    Dataset
     */
    public final static String DELETE_DATASET="DELETE_DATASET";
    public final static String DELETE_DEFAULT_DATASET="DELETE_DEFAULT_DATASET";
    public final static String CREATE_DEFAULT_DATASET="CREATE_DEFAULT_DATASET";

    /*
    MicroServices
     */
    public final static String RESTART_MICROSERVICE="RESTART_MICROSERVICE";

    /*
    Process
     */
    public final static String CREATE_PROCESS_SET = "CREATE_PROCESS_SET";
    public final static String UPDATE_PROCESS_DEPENDED_ARRAY = "UPDATE_PROCESS_DEPENDED_ARRAY";

    public final static List<String>processTableNameList=asList(
            RESET_TO_DEFAULT_DATASET,RESET_ALL_TO_DEFAULT_DATASET,CREATE_DEFAULT_DATASET
    );
    public final static List<String>processDataSetNameList=asList(
            DELETE_DATASET,DELETE_DEFAULT_DATASET,CREATE_DEFAULT_DATASET
    );
    public final static List<String>processMicroServiceNameList=asList(
            RESTART_MICROSERVICE
    );
    public final static List<String>processDatabaseNameList=asList(
    );
    public final static List<String>processProcessNameList=asList(
    );

    public final static List<String>processNameList=asList(
            DELETE_DATASET,DELETE_DEFAULT_DATASET,RESET_TO_DEFAULT_DATASET,RESET_ALL_TO_DEFAULT_DATASET,RESTART_MICROSERVICE,
            DELETE_TABLE,CREATE_DEFAULT_DATASET
    );

    public final static List<String>procedureProcessNameList=asList(
            RESET_TO_DEFAULT_DATASET
    );
}
