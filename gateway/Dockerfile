FROM adoptopenjdk/openjdk11:alpine-jre

COPY target/gateway.jar app.jar

ENTRYPOINT ["java", "-jar","app.jar"]