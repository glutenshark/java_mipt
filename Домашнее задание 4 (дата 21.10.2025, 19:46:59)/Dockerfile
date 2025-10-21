FROM eclipse-temurin:17-jdk as build
WORKDIR /app
COPY . .
RUN ./gradlew --no-daemon clean bootJar
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/libs/java-mipt-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
