FROM openjdk:8-jdk-alpine

VOLUME /tmp

RUN mkdir /spring-boot-bestpractice
WORKDIR /spring-boot-bestpractice

RUN adduser -S sboot

USER sboot

ENV JAVA_OPTS=""

ENV SPRING_PROFILES_ACTIVE=loc

COPY . /spring-boot-bestpractice