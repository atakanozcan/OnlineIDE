# project Dockerfile
FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

CMD java -jar -Dspring.profiles.active=default /app/app.jar
