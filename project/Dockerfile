# project Dockerfile
FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

CMD java -jar -Dspring.profiles.active=default /app/app.jar
ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java -jar $JAVA_OPTS -Dspring.profiles.active=default /app/app.jar"]
