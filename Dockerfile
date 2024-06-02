# Use a base image with Java 17
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven build artifact (jar file) to the working directory
COPY target/employee-service-0.0.1-SNAPSHOT.jar /app/employee-service.jar

# Expose the port the application runs on
EXPOSE 8086

# Set the command to run the application
ENTRYPOINT ["java", "-jar", "employee-service.jar"]
