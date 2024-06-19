# Use a base image with Java and Spring Boot dependencies
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file from the host to the container
COPY target/homework-0.0.1-SNAPSHOT.jar app.jar

# Set environment variables for the Spring Boot application
ENV SPRING_DATASOURCE_URL=jdbc:h2:~/H2db/HansabDb;AUTO_SERVER=TRUE;MODE=MySQL
ENV SPRING_DATASOURCE_USERNAME=sa
ENV SPRING_DATASOURCE_PASSWORD=

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
