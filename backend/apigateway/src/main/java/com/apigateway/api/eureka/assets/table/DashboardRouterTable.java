package com.apigateway.api.eureka.assets.table;

import java.util.List;

/**
 * Router table for Dashboard service.
 */
public class DashboardRouterTable {
    /** The ID for the Dashboard service. */
    public static final String DASH_BOARD_ID = "DASHBOARD";

    /** URI for creating a task. */
    public static final String URI_DASH_BOARD_CREATE_TASK = "/task/createTask";

    /** URI for getting a task by ID. */
    public static final String URI_DASH_BOARD_GET_TASK_BY_ID = "/task/";

    /** URI for getting all tasks. */
    public static final String URI_DASH_BOARD_GET_ALL_TASK = "/task/all";

    /** ----------- TICKET ----------- */

    public static final String URI_DASH_BOARD_CREATE_TICKET= "/ticket/";

    public static final String URI_DASH_BOARD_GET_TICKET_BY_ID= "/ticket/";

    public static final String URI_DASH_BOARD_GET_ALL_TICKETS= "/ticket/";

    public static final String URI_DASH_BOARD_DELETE_TICKET_BY_ID= "/ticket/";

    public static final String URI_DASH_BOARD_UPDATE_TICKET_STATUS= "/ticket/status/";

    public static final String URI_DASH_BOARD_UPDATE_TICKET= "/ticket/";

    public static final String URI_DASH_BOARD_GET_ALL_TICKET_TABLE_FILTER= "/ticket/filter/table";

    public static final String URI_DASH_BOARD_GET_TICKET_TABLE_FILTER_BY_ID= "/ticket/filter/table/";

    public static final String URI_DASH_BOARD_CREATE_TICKET_TABLE_FILTER= "/ticket/filter/table";

    public static final String URI_DASH_BOARD_UPDATE_TICKET_TABLE_FILTER= "/ticket/filter/table";

    public static final String URI_DASH_BOARD_DELETE_TICKET_TABLE_FILTER_BY_ID= "/ticket/filter/table/";


}
