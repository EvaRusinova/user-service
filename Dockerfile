# Use an official OpenJDK runtime as the base image
FROM openjdk:20-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the compiled Spring Boot application JAR file into the container at /app
COPY target/user-service-1.0.0.jar app.jar

# Expose the port that the Spring Boot application listens on
EXPOSE 8081

# Define the command to run the Spring Boot application when the container starts, and to utilize the environment variable I_AM_SEXY and pass it as a Java system property
ENTRYPOINT ["sh", "-c", "java -DI_AM_SEXY=$I_AM_SEXY -jar app.jar"]
