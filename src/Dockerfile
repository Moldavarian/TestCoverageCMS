FROM openjdk:21
WORKDIR /app
COPY target/TestCoverageCms-1.0-SNAPSHOT.jar test-coverage-cms.jar
ENTRYPOINT ["java","-jar","test-coverage-cms.jar"]