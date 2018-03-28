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
├── main
│   ├── java
│   │   └── com
│   │       └── bestpractice
│   │           └── api
│   │               ├── App.java
│   │               ├── common
│   │               │   ├── config
│   │               │   ├── filter
│   │               │   ├── property
│   │               │   └── util
│   │               │
│   │               ├── controller
│   │               │   ├── Advice.java
│   │               │   ├── v1
│   │               │   └── v2
│   │               │
│   │               ├── domain
│   │               │   ├── entity
│   │               │   ├── model
│   │               │   ├── repository
│   │               │   └── role
│   │               │
│   │               ├── exception
│   │               │
│   │               └── service
│   │
│   └── resources
│       ├── application-dev.yml
│       └── application-local.yml
└── test
    ├── java
    │   └── com
    │       └── bestpractice
    │           └── api
    │               ├── AppTests.java
    │               │
    │               ├── common
    │               │
    │               ├── controller
    │               │
    │               ├── repository
    │               │
    │               └── service
    │
    └── resources
        └── application-test.yml
```