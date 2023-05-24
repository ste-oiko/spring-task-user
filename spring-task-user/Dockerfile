##Java version
#FROM openjdk:19
#VOLUME /tmp
#
#COPY pom.xml ./
#COPY src ./src
#RUN mvn clean package
#EXPOSE 8080
#
#CMD ["java", "-jar", "target/spring-task-user-0.0.1-SNAPSHOT.jar"]
#CMD [ "serve", "-s", "build" ]

FROM openjdk:11-jdk-slim
ARG JAR_FILE="target/spring-task-user-0.0.1-SNAPSHOT.jar"
COPY target/${JAR_FILE} /app.jar
CMD ["java", "-jar", "/app.jar"]


#FROM postgres:latest
#
#LABEL description="Database for spring-task-user"
#
## Copy initialization scripts (optional)
#COPY *.yaml ./src/main/resources/db.changelog/
#COPY *.sql ./src/main/resources/db.changelog/changes/
#
## Expose the PostgreSQL port
#EXPOSE 5432