FROM java:8
COPY . /var/www/java
WORKDIR /var/www/java
RUN ./gradlew build
EXPOSE 8000
CMD ["java","-jar","./build/libs/WebServerDemo-1.0-SNAPSHOT.jar"]