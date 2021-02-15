FROM openjdk:11
EXPOSE 8000
ADD target/*.jar zuul.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /zuul.jar" ]