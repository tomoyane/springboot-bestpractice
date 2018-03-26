## SpringBoot best practice
Write to about Spring Boot best practice architecture

## Environment
### Local environment

Local development property file is application-loc.yml.

Working on docker container
  * MySQL image
  * openjdk:8-jdk image

### Develop environment
Develop development property file is application-dev.yml.

Working on Heroku
  * Postgres SQL
  * gradle buldpack

## Architecture
```
spring-boot-bestpracite
|- main
|    |-java
|    |    |-com.bestpractice.api
|    |    |    |
|    |    |    |- common
|    |    |    |    |- config
|    |    |    |    |- filter
|    |    |    |    |- property 
|    |    |    |    |- util
|    |    |    |
|    |    |    |- controller
|    |    |    |    |- v1
|    |    |    |    |- v2
|    |    |    |    |- Advice
|    |    |    |    
|    |    |    |- domain
|    |    |    |    |- entity
|    |    |    |    |- model
|    |    |    |    |- repository
|    |    |    |    |- role
|    |    |    |
|    |    |    |- exception
|    |    |    |
|    |    |    |- service
|    |    |    |
|    |    |    |- App
|    |    |    
|    |-resource
|    |    |- application-dev01.yml
|    |    |- application-dev02.yml
|    |    |- application-loc.yml
|           
|- test
|    |-java
|    |    |-com.bestpractice.api
|    |    |    |- controller
|    |    |    |    |- v1
|    |    |    |    |- v2
|    |    |    |
|    |    |    |- repository
|    |    |    |  
|    |    |    |- service
|    |    |    |
|    |    |    |- MySqlContainerTest
```