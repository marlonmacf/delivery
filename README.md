# Microservices with Spring Cloud and Spring Boot

Micro services architecture using Spring Cloud and Spring Boot frameworks from Pivotal, with enphase on some concepts like Centralized Configuration, Circuit Braker, Service Registry and Gateway, through other dependencies like Eureka, Hystrix and Zuul from Netflix.

## Configuration Server

This project centralises all the configuration of our microservices in only one place.
The configuration will be located remotely, on other GitHub project.
The configuration server will read the information of those repositories and delivery through HTTP requests.

###### Dependencies

- **spring-boot-starter-actuator**: Production ready features to help you monitor and manage your application.
- **spring-boot-starter-web**: Full-stack web development with Tomcat and Spring MVC.
- **spring-boot-starter-test**: Unit test, integration test, Api test, etc.
- **spring-cloud-config-server**: Central management for configuration via a git or svn backend.

###### Configurations

- **application.yml**: This file will override spring boot configs on the application initialization.
- **bootstrap.yml**: Used to define parent configs for the application initialization, like the spring cloud app name or the server config conection.

## Service Registry (Eureka)

Manager the status and the location of our microservices.
Eureka is a REST Service, mainly used into AWS cloud to locate services to be able to do load balancing the service failover.

###### Dependencies

- **spring-boot-starter-actuator**: Production ready features to help you monitor and manage your application.
- **spring-boot-starter-web**: Full-stack web development with Tomcat and Spring MVC.
- **spring-boot-starter-test**: Unit test, integration test, Api test, etc.
- **spring-cloud-starter-config**: Allows us to easily define the config server host through the bootstrap.yml file.
- **spring-cloud-starter-netflix-eureka-server**: Upgrade the spring boot application to an Eureka server application.

###### Configurations

- **bootstrap.yml**: Used to define parent configs for the application initialization, like the spring cloud app name or the server config conection.

## Security Server

Authorization and Authentication with Spring Security OAuth:
1. The client requests an authorization to the **resource owner**.
2. The client receives a credential from the resource owner (oAuth2 standards).
3. The client requests a token to the **authorization server**.
4. The authorization server validates the client with the info from the resource server and returns the **token** access.
5. The client request access to te resource server using the token.
6. The resource server validates the token and grants access for the client.

###### Dependencies

- **spring-boot-starter-actuator**: Production ready features to help you monitor and manage your application.
- **spring-boot-starter-web**: Full-stack web development with Tomcat and Spring MVC.
- **spring-boot-starter-test**: Unit test, integration test, Api test, etc.
- **spring-boot-starter-security**:  Starter for using Spring Security.
- **spring-boot-starter-data-mongodb**: JDBC driver for MongoDB NoSQL Database.
- **spring-cloud-starter-config**: Allows us to easily define the config server host through the bootstrap.yml file.
- **spring-cloud-starter-netflix-eureka-client**: Allow access to the Eureka server application.

###### Configurations

- **bootstrap.yml**: Used to define parent configs for the application initialization, like the spring cloud app name or the server config conection.

...

## References

https://coderef.com.br/arquitetura-de-microservices-com-spring-cloud-e-spring-boot-parte-1-b5c9288df66d

https://github.com/rafaelcam/delivery

https://github.com/rafaelcam/delivery-configss

https://egkatzioura.com/2016/09/27/spring-boot-with-spring-security-with-nosql-database/