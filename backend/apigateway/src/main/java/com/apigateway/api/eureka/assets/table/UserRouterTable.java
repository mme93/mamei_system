package com.apigateway.api.eureka.assets.table;

/**
 * Router table for User service.
 */
public class UserRouterTable {
    /** The ID for the User service. */
    public static final String USER_ID = "USER";

    /** URI for rebuilding the database. */
    public static final String DATABASE_MANAGER_REBUILD_DATABASE = "/database/rebuild";

    /** URI for initializing the user database. */
    public static final String URI_USER_DATABASE_INIT = "/user/db/init";

    public static final String ACCOUNT_BY_USER_ID="/user/account/userId/";

}
