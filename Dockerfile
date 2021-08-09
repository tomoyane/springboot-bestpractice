FROM openjdk:8-jdk-alpine

VOLUME /tmp

RUN mkdir /spring-boot-bestpractice

COPY . /spring-boot-bestpractice
WORKDIR /spring-boot-bestpractice

RUN ./gradlew clean build -x test && \
    cp build/libs/*.jar spring-boot-bestpractice.jar

ENTRYPOINT ["sh","-c","java -jar spring-boot-bestpractice.jar"]
