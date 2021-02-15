FROM openjdk:11
EXPOSE 8001
ADD target/*.jar ms-pricegenerator.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /ms-pricegenerator.jar" ]