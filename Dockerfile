FROM openjdk:8-jdk-alpine

VOLUME /tmp

ENV appName="spring-boot-bestpractice"
ENV version="0.0.1"

RUN mkdir /spring-boot-bestpractice
WORKDIR /spring-boot-bestpractice

RUN adduser -S sboot

USER sboot

COPY . /spring-boot-bestpractice

CMD java -Djava.security.egd=file:/dev/./urandom -jar build/libs/${appName}-${version}-SNAPSHOT.jar