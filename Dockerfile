FROM openjdk:19-jdk-alpine
RUN adduser -h /home/javauser -D -u 1001 javauser
RUN mkdir /home/javauser/java
RUN chown -R javauser:javauser /home/javauser/java

USER javauser
WORKDIR /home/javauser/java

ENTRYPOINT /home/javauser/java/mvnw clean install && /home/javauser/java/mvnw spring-boot:run
