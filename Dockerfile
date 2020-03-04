FROM mcr.microsoft.com/java/jre:11u5-zulu-alpine
VOLUME /tmp
EXPOSE 8080
RUN addgroup -S app && adduser -S -H -D app -G app
ADD --chown=app:app ./build/libs/app.jar /app.jar
USER app
RUN touch /app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
