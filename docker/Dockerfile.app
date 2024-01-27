FROM openjdk:17.0.1-jdk-buster

VOLUME /tmp

RUN mkdir /spring-boot-bestpractice

WORKDIR /spring-boot-bestpractice
COPY build/libs/*.jar spring-boot-bestpractice.jar
COPY src/main/resources/* /spring-boot-bestpractice

ENTRYPOINT ["sh","-c","java -jar spring-boot-bestpractice.jar"]
