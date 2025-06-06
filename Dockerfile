# syntax=docker/dockerfile:1

# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the built application from the build stage
COPY --from=build /app/target/eShop-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]