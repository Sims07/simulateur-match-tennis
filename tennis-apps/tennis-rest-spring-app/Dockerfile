FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.1.13-alpine-slim
VOLUME /tmp
COPY target/tennis-rest-spring-app-2.2.2.RELEASE.jar app.jar
RUN chmod 777 app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]