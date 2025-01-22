# Use a more recent version of OpenJDK (e.g., 20)
FROM openjdk:23-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the local machine to the container
COPY target/tftdle-0.0.1-SNAPSHOT.jar /app/tftdle-backend.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "tftdle-backend.jar"]
