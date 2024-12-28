# Use the official OpenJDK image as a base
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target directory to the working directory
COPY ./target/inventory-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your application runs on
EXPOSE 9091

# Command to run the application
CMD ["java", "-jar", "app.jar"]