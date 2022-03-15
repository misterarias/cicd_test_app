FROM openjdk:11.0-jre
COPY target/demo-0.0.1-SNAPSHOT.jar server.jar
ENTRYPOINT ["java", "-jar", "server.jar"]
