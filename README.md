
[![Build Status](http://www.concourse.developer-tm.com:8080/api/v1/teams/main/pipelines/springboot-bestpractice-pipeline/jobs/unit-test/badge)](https://www.concourse.developer-tm.com/teams/main/pipelines/springboot-bestpractice-pipeline/jobs/unit-test/builds/34)

## SpringBoot best practice 
Write to about Spring Boot best practice architecture.

## Build
Git clone
```bash
git clone https://github.com/tomo0111/springboot-bestpractice.git
```

Run test
```bash
./gradlew test
```

Docker image build
 * Build SpringBoot best practice application.
 * Use docker for local development.
   * MySQL
   * Redis
   * OpenJDK 

```bash
docker-compose -f docker-compose-local.yml build
```

Run container
```bash
docker-compose -f docker-compose-local.yml up -d
```

##  Pipeline

set target
```
fly -t tomohito login -c http://www.concourse.developer-tm.com:8080
```

all delete target
```
fly logout -a
```



## Environment
### Local environment

Local development property file is application-loc.yml.

Working on docker container.
 * Docker Image
   * MySQL
   * Redis
   * OpenJDK

Cassandra cluster.
 * CentOS7 virtual machine 
 * 3 nodes

#### MySQL
Sample query is sql/mysql_sample.sql file.

Sample Class
 * UserEntity.java
 * UserService.java
 * UserRepository.java
 * InfoEntity.java
 * InfoService.java
 * InfoRepository.java
   
#### Redis
Sample Class
 * UserService

#### Cassandra
Sample Class

### Develop environment
Develop development property file is application-dev.yml.

Working on Heroku
  * Gradle build pack
  * Postgres SQL

## Authentication and Authorization
Spring security.

JWT.

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
