.PHONY: run test build clean docker
run:
	./gradlew bootRun
test:
	./gradlew test
build:
	./gradlew clean bootJar
clean:
	./gradlew clean
docker:
	docker build -t java-mipt .
	docker run --rm -p 8080:8080 java-mipt
