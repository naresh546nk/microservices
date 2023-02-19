FROM adoptopenjdk/openjdk11:alpine-jre

COPY target/eurekaservice.jar app.jar

ENTRYPOINT ["java", "-jar","app.jar"]