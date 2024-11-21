FROM openjdk:17-alpine

ARG JAR_FILE=./labworks-service/target/labworks-service.jar

COPY ${JAR_FILE} .

ENTRYPOINT ["java", "-jar", "/labworks-service.jar"]