FROM openjdk:17

COPY target/aluraflix-0.0.1-SNAPSHOT.jar aluraflix-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/aluraflix-0.0.1-SNAPSHOT.jar"]
