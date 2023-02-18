FROM adoptopenjdk/openjdk11:alpine-jre

COPY target/configserver.jar app.jar

ENTRYPOINT ["java", "-jar","app.jar"]