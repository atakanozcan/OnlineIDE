# compiler Dockerfile
FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8082

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java -jar $JAVA_OPTS /app/app.jar"]
