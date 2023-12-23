# Project Overview

## Start Backend

The backend project, which includes various Spring Boot micro-services, can be started in different ways. 

Each individual service contains a Dockerfile, which can be created using "**docker build -t IMAGENAME .**" builds an image and can be called with "**docker run -d -p 8080:8080 IMAGENAME:latest**". 

Furthermore, the project in the backend directory the Docker-Compose file can be called with “**docker-compose up**”.

- docker-compose -f docker-compose-services.yml up

## Port-Mapping

### Services Registry

| Services                    | Port | Name                     |
|-----------------------------|------|--------------------------|
| ServiceregistryApplication  | 8761 |*Is the Service Registry* | 

### System Services Range 9000-9049

| Services                       | Port | Name                      |
|--------------------------------|------|---------------------------|
| ApigatewayApplication          | 9000 | APIGATEWAY                |
| SystemmanagerApplication       | 9001 | SYSTEM                    |
| ConfigmanagerApplication       | 9002 | *Not in Service Registry* |
| HealtmanagerApplication        | 9003 | HEALTHMANAGER             |
| SecuritygatewayApplication     | 9004 | SECURITYGATEWAY           |

### Project Services Range 9050-9099

| Services                | Port | Name          |
|-------------------------|------|---------------|
| UserApplication         | 9050 | USER          |
| SudokumanagerApplication| 9051 | SUDOKU        |
| DashboardApplication    | 9052 | DASHBOARD     |
| ShoppinglistApplication | 9053 | SHOPPINGLIST  |

## Service Description

### ServiceregistryApplication

Registers all Spring Boot microservices by name and lists them. All registered services can be found
at http://localhost:8761.

### ApigatewayApplication

Serves as the first point of contact for all requests and distributes them to the target services after you have passed the security check from the security gateway.

Takes care of all areas of network security such as token procedures.


### SystemmanagerApplication

*Central logging and monitoring*:

1) The system service can serve as a central contact point for logging and monitoring. This would allow log data and monitoring information to be consolidated system-wide.

*Caching strategies*:

2) The system service could provide centralized caching strategies to improve performance and minimize duplication of data access.

*Central event bus handling*:

3) A system service could serve as a central hub for managing event bus communications between different microservices.

### ConfigmanagerApplication

Provides the configurations for the other micro-services and cannot be found in the Eureka list.

### HealtmanagerApplication

Should monitor all micro-services and analyze the status of the services with the help of the actuator.

### SecuritygatewayApplication

Overview about current and past Security-Protocols or Events.
