FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.1.13-alpine-slim
COPY target/tennis-rest-micronaut-app-*.jar tennis-rest-micronaut-app.jar
EXPOSE 9002
CMD java -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar tennis-rest-micronaut-app.jar