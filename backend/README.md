# Project Overview

## Port-Mapping

### Services Registry

- ServiceregistryApplication:
    - Port: **8761**

### System Services Range 9000-9049

- ApigatewayApplication:
    - Port: **9000**
    - Name: **APIGATEWAY**
- SystemmanagerApplication:
    - Port: **9001**
    - Name: **SYSTEM**
- ConfigmanagerApplication:
    - Port: **9002**
    - Name: *Not in Service*
- HealtmanagerApplication:
    - Port: **9003**
    - Name: **HEALTHMANAGER**

### Project Services Range 9050-9099

- UserApplication:
    - Port: **9050**
    - Name: **USER**
- SudokumanagerApplication:
    - Port: **9051**
    - Name: **SUDOKU**
- DashboardApplication:
    - Port: **9052**
    - Name: **DASHBOARD**
- ShoppinglistApplication:
    - Port: **9053**
    - Name: **SHOPPINGLIST**

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
