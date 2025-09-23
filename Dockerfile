# --- Build stage ---
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app

# Copy Gradle wrapper and build files first
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Ensure gradlew works inside Linux container
RUN chmod +x gradlew && sed -i 's/\r$//' gradlew

# Copy the rest of the source
COPY . .

# Build the jar
RUN ./gradlew bootJar --no-daemon

# --- Runtime stage ---
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
