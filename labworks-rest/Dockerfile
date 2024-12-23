FROM openjdk:17-alpine

ARG JAR_FILE=./labworks-rest/target/labworks-rest.jar

COPY ${JAR_FILE} .

ENTRYPOINT ["java", "-jar", "/labworks-rest.jar"]