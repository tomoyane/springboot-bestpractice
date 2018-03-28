FROM openjdk:8-jdk-alpine

VOLUME /tmp

RUN mkdir /spring-boot-bestpractice
WORKDIR /spring-boot-bestpractice

RUN adduser -S sboot

USER sboot

COPY . /spring-boot-bestpractice