#Use a base image with Java 17
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven build artifact (jar file) to the working directory
COPY target/employee-service-0.0.1-SNAPSHOT.jar employee-service.jar

# Expose the port the application runs on
EXPOSE 8888

# Copy the application's jar to the container
ARG JAR_FILE=target/employee-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} api-gateway.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "employee-service.jar"]

#TODO Database connection