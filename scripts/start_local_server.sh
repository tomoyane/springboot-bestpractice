#!/bin/bash

set -e

cd ../
export SPRING_PROFILES_ACTIVE=local
export MYSQL_DB_HOST=localhost
export MYSQL_DB_NAME=test
export MYSQL_DB_USER=user
export MYSQL_DB_PASS=password
export REDIS_DB_HOST=localhost
export REDIS_DB_PORT=6379
export REDIS_DB_PASS=root
./gradlew bootRun
