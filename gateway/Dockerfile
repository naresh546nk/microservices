FROM adoptopenjdk/openjdk11:alpine-jre

COPY target/gatway.jar app.jar

ENTRYPOINT ["java", "-jar","app.jar"]