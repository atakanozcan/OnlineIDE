# frontend Dockerfile
FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 4200

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java -jar $JAVA_OPTS /app/app.jar"]
