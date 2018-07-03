# Microservices with Spring Cloud and Spring Boot

## Configuration Server

This project centralises all the configuration of our microservices in only one place.
The configuration will be located remotely, on other GitHub project.
The configuration server will read the information of those repositories and delivery through HTTP request.

###### Dependencies

- **spring-boot-starter-web**: Full-stack web development with Tomcat and Spring MVC.
- **spring-boot-starter-test**: Unit test, integration test, Api test, etc.
- **spring-boot-actuator**: Production ready features to help you monitor and manage your application.
- **spring-cloud-config-server**: Central management for configuration via a git or svn backend.

###### Configurations

- **application.yml**: This file will override spring boot configs on the application initialization.
- **bootstrap.yml**: Used to define parent configs for the application initialization, like the spring cloud app name or the server config conection.

...

## References

https://coderef.com.br/arquitetura-de-microservices-com-spring-cloud-e-spring-boot-parte-1-b5c9288df66d

https://github.com/rafaelcam/delivery

https://github.com/rafaelcam/delivery-configss