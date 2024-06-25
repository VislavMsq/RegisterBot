# Start with a base image containing Java runtime
FROM openjdk:11-jre-slim

WORKDIR /app

# The application's jar file
COPY target/*.jar /app/app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]