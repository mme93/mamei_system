package com.apigateway.api.eureka.assets.table;

/**
 * Router table for System Manager service.
 */
public class SystemManagerRouterTable {
    /** URI for checking system ping. */
    public static final String uri_system_ping = "/system/ping";

    /** URI for getting microservices status. */
    public static final String uri_system_get_microservices_status = "/system/service_status";

    /** URI for creating a task. */
    public static final String uri_system_get_create_task = "/system/protocol/create";

    /** URI for closing a task. */
    public static final String uri_system_post_close_task = "/system/protocol/close";

    /** URI for getting task protocol by ID. */
    public static final String uri_system_get_task_protocol = "/system/protocol/";

    /** URI for loading task protocol. */
    public static final String uri_system_get_load_task_protocol = "/system/protocol/load";

    /** URI for updating task comments. */
    public static final String uri_system_post_comment = "/system/protocol/comment/update";

    /** URI for getting process UI. */
    public static final String uri_system_get_process_ui = "/system/process/";

    /** URI for creating a new job process. */
    public static final String uri_system_get_process_new_job = "/system/process/newJob";

    /** URI for sorting processes. */
    public static final String uri_system_get_sort_process = "/system/process/sort";

    /** URI for setting default process. */
    public static final String uri_system_get_process_set_default = "/system/process/set_default";
}
