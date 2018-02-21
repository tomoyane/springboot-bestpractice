FROM openjdk:8-jdk-alpine

VOLUME /tmp

RUN mkdir /spring-boot-bestpractice
WORKDIR /spring-boot-bestpractice

RUN adduser -S sboot

USER sboot

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar build/libs/spring-boot-bestpractice-0.0.1-SNAPSHOT.jar" ]

COPY . /spring-boot-bestpractice