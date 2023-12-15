# Project Overview

## Start Backend

The backend project, which includes various Spring Boot micro-services, can be started in different ways. 

Each individual service contains a Dockerfile, which can be created using "**docker build -t IMAGENAME .**" builds an image and can be called with "**docker run -d -p 8080:8080 IMAGENAME:latest**". 

Furthermore, the project in the backend directory the Docker-Compose file can be called with “**docker-compose up**”.

## Port-Mapping

### Services Registry

| Services                    | Port | Name          |
|-----------------------------|------|---------------|
| ServiceregistryApplication  | 8761 |*Is the Service Registry* | 

### System Services Range 9000-9049

| Services                   | Port | Name          |
|-----------------------------|------|---------------|
| ApigatewayApplication       | 9000 | APIGATEWAY    |
| SystemmanagerApplication    | 9001 | SYSTEM        |
| ConfigmanagerApplication    | 9002 | *Not in Service Registry* |
| HealtmanagerApplication     | 9003 | HEALTHMANAGER |

### Project Services Range 9050-9099

| Services               | Port | Name          |
|-------------------------|------|---------------|
| UserApplication         | 9050 | USER          |
| SudokumanagerApplication| 9051 | SUDOKU        |
| DashboardApplication    | 9052 | DASHBOARD     |
| ShoppinglistApplication  | 9053 | SHOPPINGLIST  |

## Service Description

### ServiceregistryApplication

Registers all Spring Boot microservices by name and lists them. All registered services can be found
at http://localhost:8761.

### ApigatewayApplication

Checks the token whether it is valid or not. With the validator token, this is automatically renewed.

### SystemmanagerApplication

Is the main micro-services that takes care of distributing the requests and forwarding them.

### ConfigmanagerApplication

Provides the configurations for the other micro-services and cannot be found in the Eureka list.

### HealtmanagerApplication

Should monitor all micro-services and analyze the status of the services with the help of the actuator.
