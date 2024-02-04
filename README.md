# SpringBoot best practice
[![springboot-bestpractice](https://github.com/tomoyane/springboot-bestpractice/actions/workflows/ci.yml/badge.svg)](https://github.com/tomoyane/springboot-bestpractice/actions/workflows/ci.yml)
[![Apache License](https://img.shields.io/badge/license-Apatch-mediumpurple.svg?style=flat)](https://github.com/herts-stack/herts/blob/master/LICENSE)

About Spring Boot architecture.

This repository support multiple database by SPRING_PROFILES_ACTIVE
* RDBMS
* MongoDB
* Cassandra
* Redis

## Requirements
 * Docker engine
 * Docker Compose
 * OpenJDK 17
 * Spring Boot 2

### Docker database
MySQL8
* **Standalone**

MongoDB
* **Standalone**

Cassandra cluster.
* **3 Nodes**

## Architecture
The "app" package holds controllers for API endpoints and provides interception. The "app" package can depend on the "domain" package.

The "domain" package provides business logic and holds Spring Component Beans or Service Beans. It cannot depend on the "app" package but relies on the "infrastructure" package.

The "infrastructure" package provides data storage by holding Spring Repository Beans. It is independent and cannot depend on the "app" and "domain" packages.

The "common" package offers generic functions.

Initialization processes during server startup depend on "SPRING_PROFILES_ACTIVE" and assume the specified data store's Bean as a prerequisite for the initial startup.


### Directory structure

```bash
java
    │   │   └── com
    │   │       └── bestpractice
    │   │           └── api
    │   │               ├── Application.java
    │   │               ├── app
    │   │               │   ├── AdviceController.java
    │   │               │   ├── AppBean.java
    │   │               │   ├── InterceptorController.java
    │   │               │   ├── v1
    │   │               │   │   ├── AuthController.java
    │   │               │   │   ├── HelloController.java
    │   │               │   │   ├── RdbmsController.java
    │   │               │   │   └── UserController.java
    │   │               │   └── v2
    │   │               │       └── AuthorizationController.java
    │   │               ├── common
    │   │               │   ├── exception
    │   │               │   │   ├── BadRequest.java
    │   │               │   │   ├── Conflict.java
    │   │               │   │   ├── Forbidden.java
    │   │               │   │   ├── InternalServerError.java
    │   │               │   │   ├── NotFound.java
    │   │               │   │   ├── RequestTimeout.java
    │   │               │   │   ├── ServiceUnavailable.java
    │   │               │   │   └── UnAuthorized.java
    │   │               │   ├── property
    │   │               │   │   └── CredentialProperty.java
    │   │               │   └── util
    │   │               │       └── Util.java
    │   │               ├── domain
    │   │               │   ├── DomainBean.java
    │   │               │   ├── component
    │   │               │   │   ├── AuthComponent.java
    │   │               │   │   ├── BCryptPasswordEncryptionComponent.java
    │   │               │   │   └── RequestInfoComponent.java
    │   │               │   ├── model
    │   │               │   │   ├── AuthByEmailRequest.java
    │   │               │   │   ├── AuthByRefreshTokenRequest.java
    │   │               │   │   ├── AuthResponse.java
    │   │               │   │   ├── Credential.java
    │   │               │   │   ├── ErrorResponse.java
    │   │               │   │   ├── InfoRequest.java
    │   │               │   │   ├── InfoResponse.java
    │   │               │   │   ├── UserRequest.java
    │   │               │   │   └── UserResponse.java
    │   │               │   └── service
    │   │               │       ├── AuthService.java
    │   │               │       ├── AuthServiceImpl.java
    │   │               │       ├── InfoService.java
    │   │               │       ├── InfoServiceImpl.java
    │   │               │       ├── UserService.java
    │   │               │       └── UserServiceImpl.java
    │   │               └── infrastrucuture
    │   │                   ├── InfrastructureBean.java
    │   │                   ├── cache
    │   │                   │   ├── CacheRepository.java
    │   │                   │   ├── local
    │   │                   │   │   └── LocalCacheRepository.java
    │   │                   │   └── redis
    │   │                   │       ├── RedisCacheRepository.java
    │   │                   │       └── RedisProperty.java
    │   │                   ├── entity
    │   │                   │   ├── Info.java
    │   │                   │   ├── SharedData.java
    │   │                   │   └── User.java
    │   │                   └── persistent
    │   │                       ├── InfoPersistentRepository.java
    │   │                       ├── UserPersistentRepository.java
    │   │                       ├── cassandra
    │   │                       │   ├── CassandraInfoPersistentRepository.java
    │   │                       │   ├── CassandraUserPersistentRepository.java
    │   │                       │   └── property
    │   │                       │       └── CassandraProperty.java
    │   │                       ├── local
    │   │                       │   ├── LocalInfoPersistentRepository.java
    │   │                       │   └── LocalUserPersistentRepository.java
    │   │                       ├── mongo
    │   │                       │   ├── MongoInfoPersistentRepository.java
    │   │                       │   ├── MongoUserPersistentRepository.java
    │   │                       │   ├── entity
    │   │                       │   │   ├── MongoInfoEntity.java
    │   │                       │   │   └── MongoUserEntity.java
    │   │                       │   └── property
    │   │                       │       └── MongoProperty.java
    │   │                       └── rdbms
    │   │                           ├── RdbmsInfoPersistentRepository.java
    │   │                           └── RdbmsUserPersistentRepository.java
```

### Interceptor JWT verification

* app/InterceptorController.java

### MySQL Repository injection

* infrastructure/persistent/rdbms/RdbmsUserPersistentRepository.java
* infrastructure/persistent/rdbms/RdbmsInfoPersistentRepository.java

### MongoDB Repository injection

* infrastructure/persistent/mongo/RdbmsUserPersistentRepository.java
* infrastructure/persistent/mongo/RdbmsInfoPersistentRepository.java

### Cassandra Repository injection

* infrastructure/persistent/cassandra/RdbmsUserPersistentRepository.java
* infrastructure/persistent/cassandra/RdbmsInfoPersistentRepository.java

### Redis Repository injection

* infrastructure/cache/redis/RedisCacheRepository.java

## Getting Started

Start database process
```bash
# RDBMS
docker-compose run -p 3306:3306 mysql_db

# Mongo
docker-compose run -p 27017:27017 mongo_db

# Cassandra
docker-compose up cassandra_01 cassandra_02 cassandra_03
```

Start api server via script
```bash
# RDBMS
./scripts/start_server.sh --spring_profile=local,db_rdbms

# Mongo
./scripts/start_server.sh --spring_profile=local,db_mongo

# Cassandra
./scripts/start_server.sh --spring_profile=local,db_cassandra
```

## License
[Apache-2.0](https://github.com/tomoyane/springboot-bestpractice/blob/master/LICENSE)
