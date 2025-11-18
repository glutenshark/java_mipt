
set -euo pipefail

BASE_DIR=$'Домашнее задание 8. Дедлайн 20.11.2025'
if [[ -e "$BASE_DIR" ]]; then
  exit 1
fi

mkdir -p "$BASE_DIR"/src/main/java/com/example/demo "$BASE_DIR"/src/main/resources

cat > "$BASE_DIR/settings.gradle" <<'EOF'
rootProject.name = 'homework-8-docker'
EOF

cat > "$BASE_DIR/build.gradle" <<'EOF'
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.3.4'
    id 'io.spring.dependency-management' version '1.1.6'
}
group = 'com.example'
version = '0.0.1-SNAPSHOT'
java {
    sourceCompatibility = JavaVersion.VERSION_17
}
repositories {
    mavenCentral()
}
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'org.postgresql:postgresql'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
tasks.named('test') {
    useJUnitPlatform()
}
EOF

cat > "$BASE_DIR/src/main/java/com/example/demo/DemoApplication.java" <<'EOF'
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
EOF

cat > "$BASE_DIR/src/main/java/com/example/demo/MessageController.java" <<'EOF'
package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {
    @GetMapping("/api/messages")
    public List<String> getMessages() {
        return List.of("message-1", "message-2");
    }
}
EOF

cat > "$BASE_DIR/src/main/resources/application.properties" <<'EOF'
spring.datasource.url=jdbc:postgresql://${DB_HOST}:5432/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=none
EOF

cat > "$BASE_DIR/Dockerfile" <<'EOF'
FROM gradle:jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

FROM openjdk:17-jre-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
EOF

cat > "$BASE_DIR/docker-compose.yml" <<'EOF'
version: "3.8"
services:
  db:
    image: postgres:15
    environment:
      POSTGRES_USER: appuser
      POSTGRES_PASSWORD: apppassword
      POSTGRES_DB: appdb
    ports:
      - "5432:5432"
  app:
    build: .
    depends_on:
      - db
    ports:
      - "8080:8080"
    environment:
      DB_HOST: db
      DB_NAME: appdb
      DB_USER: appuser
      DB_PASSWORD: apppassword
EOF
