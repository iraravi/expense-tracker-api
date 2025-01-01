# Use a base image with JDK 17 (or your preferred version)
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file to the container
COPY build/libs/expense-tracker-api-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application runs on (default is 8080)
EXPOSE 9000

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]