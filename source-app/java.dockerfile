# Java
ARG JAVA_VERSION=17
FROM amazoncorretto:17-alpine3.17
MAINTAINER devops-ipi
COPY ./downloaded-artifact/social-network-0.0.1-SNAPSHOT.jar social-network.jar
EXPOSE 8080
RUN useradd -m -d /home/docker-ipi docker-ipi
USER docker-ipi
ENTRYPOINT ["java","-jar","/social-network.jar"]
