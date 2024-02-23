package com.apigateway.api.eureka.assets.table;

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
}
