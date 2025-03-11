FROM openjdk:22-jdk-slim

WORKDIR /app

COPY target/transacao-api-0.0.1-SNAPSHOT.jar /app/transacao-api.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/transacao-api.jar"]