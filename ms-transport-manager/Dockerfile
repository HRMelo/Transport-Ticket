FROM openjdk:11
EXPOSE 8002
ADD target/*.jar ms-trasport-manager.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /ms-trasport-manager.jar" ]