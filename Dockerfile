FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY /build/libs/BookManagementSystem-0.0.1-SNAPSHOT.jar .
ENTRYPOINT java -jar BookManagementSystem-0.0.1-SNAPSHOT.jar