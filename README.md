## SpringBoot best practice
Write to about Spring Boot best practice architecture

## Environment
* application-loc.yml

This application.yml is 
  * Working on docker container
    * MySQL image
    * openjdk:8-jdk image

* application-dev.yml
  * Working on Heroku
    * Postgres SQL
    * gradle buldpack

## Architecture
```
spring-boot-bestpracite
|-common
|     |- config
|     |- filter
|     |- property 
|     |- util
|
|-controller
|     |- v1
|     |- v2
|     |- Advice
|
|-domain
|     |- entity
|     |- model
|     |- repository
|     |- role
|
|-exception
|
|-service
|
|-App
```