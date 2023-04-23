FROM openjdk:17-jdk-alpine
ARG JAR_File=target/*.jar
COPY ./target/backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]