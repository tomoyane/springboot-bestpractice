#!/bin/bash

set -e

ver=$(cat build.gradle | grep version | sed 's/version =//g' | sed "s/'//g" | sed -n 1P | sed 's/\"//g' | sed 's/ //g')

./gradlew clean build -x test
docker login -u "${DOCKER_USER}" -p "${DOCKER_PASSWORD}"
docker build -t tomohito/springboot-bestpractice:"${ver}" -t tomohito/springboot-bestpractice:latest .
docker push tomohito/springboot-bestpractice
