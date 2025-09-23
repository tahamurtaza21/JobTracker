# Use lightweight OpenJDK image
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app

# Copy Gradle wrapper files first (better layer caching)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Give execution permission
RUN chmod +x gradlew

# Now copy the rest
COPY . .

# Build the app
RUN ./gradlew bootJar
