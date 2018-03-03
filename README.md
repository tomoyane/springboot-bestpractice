## SpringBoot best practice
Write to about Spring Boot best practice architecture

## Environment
* application-loc.yml
  * Working on docker container
    * MySQL container
    * openjdk:8-jdk container
* application-dev.yml
  * Working on Heroku
    * Postgres SQL
    * gradle buldpack


## Architecture
```
spring-boot-bestpracite
|-common
|     |- config
|     |- property 
|     |- util
|
|-controller
|     |- v1
|     |- v2
|
|-domain
|     |- entity
|     |- model
|     |- repository
|
|-exception
|
|-service
|-App
```