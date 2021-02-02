# compiler Dockerfile
FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8082

CMD java -jar /app/app.jar
