FROM openjdk:21-jdk-slim
WORKDIR /app
COPY /target/GAI_Cars-0.0.1-SNAPSHOT.jar /app/GAI_Cars.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "GAI_Cars.jar"]