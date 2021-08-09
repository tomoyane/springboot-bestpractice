# SpringBoot best practice
[![springboot-bestpractice](https://github.com/tomoyane/springboot-bestpractice/actions/workflows/ci.yml/badge.svg)](https://github.com/tomoyane/springboot-bestpractice/actions/workflows/ci.yml)
[![MIT License](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](https://github.com/tomoyane/springboot-bestpractice/blob/master/LICENSE.txt)

About Spring Boot best practice architecture.

## Environment variable

|Name|Description|
|---|---|
|SPRING_PROFILES_ACTIVE|Spring runtime environment|
|MYSQL_DB_HOST|Database host|
|MYSQL_DB_NAME|Database name|
|MYSQL_DB_USER|Database username|
|MYSQL_DB_PASS|Database password|
|REDIS_DB_HOST|Redis host|
|REDIS_DB_PORT|Redis port|
|REDIS_DB_PASS|Redis password|

### Spring Active Profiles (Local)

Local development property file is application-local.yml.

```bash
$ export SPRING_PROFILES_ACTIVE="local"
```

Working on docker container.
 * Docker Image
   * MySQL
   * Redis
   * OpenJDK

Cassandra cluster.
 * CentOS7 virtual machine 
 * 3 nodes

#### MySQL (5.5)
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

### Spring Active Profiles (Develop)
Develop development property file is application-dev.yml.

```bash
$ export SPRING_PROFILES_ACTIVE="dev"
```

## Build
Git clone.
```bash
$ git clone https://github.com/tomoyane/springboot-bestpractice.git
```

Run test.
```bash
./gradlew test
```

Rub build.
```bash
./gradlew build 
```

### Using docker container
Docker image build
 * Build SpringBoot best practice application.
 * Use docker for local development.
   * MySQL
   * Redis
   * OpenJDK 

```bash
$ docker-compose build
```

Run container
```bash
$ docker-compose up -d
```

## Authentication and Authorization
Spring security.

JWT.

## Architecture
```
spring-boot-bestpracite
├── main
│   ├── java
│   │    └── com
│   │        └── bestpractice
│   │           └── api
│   │               ├── App.java
│   │               ├── common
│   │               │   ├── config
│   │               │   ├── property
│   │               │   └── util
│   │               │
│   │               ├── controller
│   │               │   ├── Advice.java
│   │               │   ├── v1
│   │               │   └── v2
│   │               │
│   │               ├── domain
│   │               │   ├── entity
│   │               │   ├── model
│   │               │   ├── repository
│   │               │   └── service
│   │               │
│   │               ├── exception
│   │               │
│   │               └── security
│   │                   ├── filter
│   │                   └── role
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

## License
[MIT](https://github.com/tomoyane/springboot-bestpractice/blob/master/LICENSE)
