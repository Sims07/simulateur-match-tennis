FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.1.13-alpine-slim
COPY target/tennis-api-gateway-aws-lamda-micronaut-app-*.jar tennis-api-gateway-aws-lamda-micronaut-app.jar
EXPOSE 8080
CMD java -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar tennis-api-gateway-aws-lamda-micronaut-app.jar