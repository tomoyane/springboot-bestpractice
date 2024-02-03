#!/bin/bash

set -e

if [ "$#" -eq 0 ]; then
    echo "Default argument"
fi

SPRING_PROFILE="local,db_local"

while [ "$#" -gt 0 ]; do
    case "$1" in
        --spring_profile=*)
            SPRING_PROFILE="${1#*=}"
            ;;
        *)
            echo "Invalid: $1"
            exit 1
            ;;
    esac
    shift
done

echo "SPRING_PROFILES_ACTIVE: $SPRING_PROFILE"

export SPRING_PROFILES_ACTIVE=$SPRING_PROFILE
export MYSQL_DB_HOST=localhost
export MYSQL_DB_NAME=test
export MYSQL_DB_USER=user
export MYSQL_DB_PASS=password
export REDIS_DB_HOST=localhost
export REDIS_DB_PORT=6379
export REDIS_DB_PASS=root

./gradlew bootRun
