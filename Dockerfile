# Use lightweight OpenJDK image
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app

# Copy Gradle/Maven files and source
COPY . .

# Build the app (Gradle example, switch to mvnw if Maven)
RUN ./gradlew bootJar

# --- Runtime image ---
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose port for Render
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
