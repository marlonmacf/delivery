# Microservices with Spring, Netflix and MongoDB

Microservices architecture using Spring Boot, Data, Cloud and Security frameworks from Pivotal, with enphase on some concepts like Centralized Configuration, Circuit Braker, Service Registry and Services Gateway, through other dependencies like Netflix Eureka, Hystrix and Zuul.

## Configuration Server

This project centralises all the configuration of our microservices in only one place.
The configuration will be located remotely, on other GitHub project.
The configuration server will read the information of those repositories and delivery through HTTP requests.

###### Dependencies

- **spring-boot-starter-web**: Full-stack web development with Tomcat and Spring MVC.
- **spring-boot-starter-actuator**: Production ready features to help you monitor and manage your application.
- **spring-cloud-config-server**: Central management for configuration via a git or svn backend.

###### Configurations

- **application.yml**: This file will override spring boot configs on the application initialization.
- **bootstrap.yml**: Used to define parent configs for the application initialization, like the spring cloud app name or the server config conection.

## Service Registry (Eureka)

Manager the status and the location of our microservices.
Eureka is a REST Service, mainly used into AWS cloud to locate services to be able to do load balancing and service failover.

###### Dependencies

- **spring-boot-starter-web**: Full-stack web development with Tomcat and Spring MVC.
- **spring-boot-starter-actuator**: Production ready features to help you monitor and manage your application.
- **spring-cloud-starter-config**: Allows us to easily define the config server host through the bootstrap.yml file.
- **spring-cloud-starter-netflix-eureka-server**: Upgrade the spring boot application to an Eureka server application.

###### Configurations

- **bootstrap.yml**: Used to define parent configs for the application initialization, like the spring cloud app name or the server config conection.

## Auth Service (Security)

Authorization and Authentication with Spring Security.

###### Dependencies

- **spring-boot-starter-web**: Full-stack web development with Tomcat and Spring MVC.
- **spring-boot-starter-actuator**: Production ready features to help you monitor and manage your application.
- **spring-boot-starter-security**:  Starter for using Spring Security.
- **spring-boot-starter-data-mongodb**: JDBC driver for MongoDB NoSQL Database.
- **spring-cloud-starter-config**: Allows us to easily define the config server host through the bootstrap.yml file.
- **spring-cloud-starter-netflix-eureka-client**: Allow access to the Eureka server application.

###### Configurations

- **bootstrap.yml**: Used to define parent configs for the application initialization, like the spring cloud app name or the server config conection.

## Order Service (CRUD)

Ordering CRUD - without security so far.

###### Dependencies

- **spring-boot-starter-web**: Full-stack web development with Tomcat and Spring MVC.
- **spring-boot-starter-actuator**: Production ready features to help you monitor and manage your application.
- **spring-boot-starter-security**:  Starter for using Spring Security.
- **spring-boot-starter-data-mongodb**: JDBC driver for MongoDB NoSQL Database.
- **spring-cloud-starter-config**: Allows us to easily define the config server host through the bootstrap.yml file.
- **spring-cloud-starter-netflix-eureka-client**: Allow access to the Eureka server application.

###### Configurations

- **bootstrap.yml**: Used to define parent configs for the application initialization, like the spring cloud app name or the server config conection.

...

## References

https://coderef.com.br/arquitetura-de-microservices-com-spring-cloud-e-spring-boot-parte-1-b5c9288df66d

https://egkatzioura.com/2016/09/27/spring-boot-with-spring-security-with-nosql-database/

https://www.logicbig.com/tutorials/spring-framework/spring-security/authorization-config.html