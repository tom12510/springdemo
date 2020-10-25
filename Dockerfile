FROM java:8
VOLUME /tmp
ADD *.jar app.jar
EXPOSE 9090
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
