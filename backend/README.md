# Project Overview

## Port-Mapping

### Services Registry
- ServiceregistryApplication: 8761
- 
### System Services Range 9000-9049
- ApigatewayApplication: 9000
- SystemmanagerApplication: 9001
- ConfigmanagerApplication: 9002
- HealtmanagerApplication: 9003

### Project Services Range 9050-9099
- UserApplication: 9050
- SudokumanagerApplication: 9051
- DashboardApplication: 9052
- ShoppinglistApplication: 9053

## Service Description

### ServiceregistryApplication

Registers all Spring Boot microservices by name and lists them. All registered services can be found at http://localhost:8761.

### ApigatewayApplication

Checks the token whether it is valid or not. With the validator token, this is automatically renewed.

### SystemmanagerApplication

Is the main micro-services that takes care of distributing the requests and forwarding them.

### ConfigmanagerApplication

Provides the configurations for the other micro-services and cannot be found in the Eureka list.

### HealtmanagerApplication

Should monitor all micro-services and analyze the status of the services with the help of the actuator.
