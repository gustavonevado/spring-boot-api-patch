FROM openjdk:11-jre-slim
ADD /target/spring-boot-api-patch.jar spring-boot-api-patch.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -jar spring-boot-api-patch.jar