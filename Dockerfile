FROM openjdk:8-jdk-alpine

VOLUME /tmp

ENV APP_NAME="spring-boot-bestpractice"
ENV VERSION="0.0.1"

ENV SPRING_PROFILES_ACTIVE="local"

ENV MYSQL_DB_HOST="mysql_db"
ENV MYSQL_DB_NAME="test"
ENV MYSQL_DB_USER="root"
ENV MYSQL_DB_PASS="root"

ENV REDIS_DB_HOST="redis_db"
ENV REDIS_DB_PORT="6379"
ENV REDIS_DB_PASS="root"

RUN mkdir /spring-boot-bestpractice
WORKDIR /spring-boot-bestpractice

COPY . /spring-boot-bestpractice

RUN cd /spring-boot-bestpractice

ENTRYPOINT ["sh","-c","java -jar -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} build/libs/${APP_NAME}-${VERSION}-SNAPSHOT.jar"]
