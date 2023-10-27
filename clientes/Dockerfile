FROM openjdk:17-jdk-slim as build
MAINTAINER david.emanuel.rp94@gmail.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

