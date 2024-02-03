# SpringBoot best practice
[![springboot-bestpractice](https://github.com/tomoyane/springboot-bestpractice/actions/workflows/ci.yml/badge.svg)](https://github.com/tomoyane/springboot-bestpractice/actions/workflows/ci.yml)
[![Apache License](https://img.shields.io/badge/license-Apatch-mediumpurple.svg?style=flat)](https://github.com/herts-stack/herts/blob/master/LICENSE)

About Spring Boot architecture.

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

You can check start server for local.

```bash
# Data store
$ cd docker
$ docker-compose up

# Application
$ cd scripts
$ ./start_server.sh 
```

Working on docker container.
 * Docker Image
   * MySQL
   * Redis
   * OpenJDK 17

Cassandra cluster.
 * CentOS7 virtual machine 
 * 3 nodes

#### MySQL (8)

Sample Class
 * UserRepository.java
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
```bash
spring-boot-bestpracite
├── LICENSE
├── README.md
├── build
│   ├── bootRunMainClassName
│   ├── classes
│   │   └── java
│   │       └── main
│   │           └── com
│   │               └── bestpractice
│   │                   └── api
│   │                       ├── Application.class
│   │                       ├── app
│   │                       │   ├── Advice.class
│   │                       │   ├── AppBean$SwaggerConfig.class
│   │                       │   ├── AppBean.class
│   │                       │   ├── v1
│   │                       │   │   ├── AuthenticationController.class
│   │                       │   │   ├── HelloController.class
│   │                       │   │   └── RdbmsController.class
│   │                       │   └── v2
│   │                       │       └── AuthorizationController.class
│   │                       ├── common
│   │                       │   ├── exception
│   │                       │   │   ├── BadRequest.class
│   │                       │   │   ├── Conflict.class
│   │                       │   │   ├── Forbidden.class
│   │                       │   │   ├── InternalServerError.class
│   │                       │   │   ├── NotFound.class
│   │                       │   │   ├── RequestTimeout.class
│   │                       │   │   ├── ServiceUnavailable.class
│   │                       │   │   └── UnAuthorized.class
│   │                       │   ├── property
│   │                       │   │   ├── CredentialProperty.class
│   │                       │   │   └── RedisProperty.class
│   │                       │   └── util
│   │                       │       └── Util.class
│   │                       ├── domain
│   │                       │   ├── DomainBean.class
│   │                       │   ├── model
│   │                       │   │   ├── AuthRequest.class
│   │                       │   │   ├── AuthResponse.class
│   │                       │   │   ├── Credential.class
│   │                       │   │   ├── Exception.class
│   │                       │   │   ├── InfoRequest.class
│   │                       │   │   ├── InfoResponse.class
│   │                       │   │   ├── UserRequest.class
│   │                       │   │   └── UserResponse.class
│   │                       │   └── service
│   │                       │       ├── AuthenticationService.class
│   │                       │       ├── InfoService.class
│   │                       │       ├── InfoServiceImpl.class
│   │                       │       ├── JsonWebTokenService.class
│   │                       │       ├── UserService.class
│   │                       │       └── UserServiceImpl.class
│   │                       ├── infrastrucuture
│   │                       │   ├── InfrastructureBean.class
│   │                       │   ├── entity
│   │                       │   │   ├── Info.class
│   │                       │   │   ├── SharedAbstract.class
│   │                       │   │   ├── SignatureKey.class
│   │                       │   │   └── User.class
│   │                       │   └── repository
│   │                       │       ├── InfoRepository.class
│   │                       │       └── UserRepository.class
│   │                       └── security
│   │                           ├── SecurityBean.class
│   │                           ├── filter
│   │                           │   ├── AuthEntryPoint.class
│   │                           │   └── PreAuthenticatedProcessingFilter.class
│   │                           └── role
│   │                               ├── AdminAuthority.class
│   │                               └── UserAuthority.class```
```

## License
[Apache-2.0](https://github.com/tomoyane/springboot-bestpractice/blob/master/LICENSE)
