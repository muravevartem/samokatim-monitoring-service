FROM bellsoft/liberica-openjdk-alpine:17
COPY target/*.jar app.jar
COPY src/main/resources/dummy-files /dummy-files
ENTRYPOINT ["java", "-jar", "./app.jar"]