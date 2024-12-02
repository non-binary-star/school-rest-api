FROM maven:3.9.4-eclipse-temurin-17 AS builder
COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY . .

RUN mvn clean install -B

FROM openjdk:17-jdk-slim

COPY --from=builder target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
