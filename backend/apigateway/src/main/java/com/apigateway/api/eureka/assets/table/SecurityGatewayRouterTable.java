package com.apigateway.api.eureka.assets.table;

/**
 * Router table for Security Gateway service.
 */
public class SecurityGatewayRouterTable {
    /** The ID for the Security Gateway service. */
    public static final String SECURITY_GATEWAY_ID = "SECURITY";

    /** URI for user login authentication. */
    public static final String URI_SECURITY_GATEWAY_LOGIN = "/security/authenticate/login";

    /** URI for checking if a token is expired. */
    public static final String URI_SECURITY_GATEWAY_IS_EXPIRED = "/security/token/isExpired";

    /** URI for user registration. */
    public static final String URI_SECURITY_GATEWAY_REGISTRATION = "/security/authenticate/registration";
}
