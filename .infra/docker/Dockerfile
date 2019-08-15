FROM java:8
COPY . /var/www/java
WORKDIR /var/www/java
RUN ./gradlew build
WORKDIR ./build/libs
EXPOSE 8000
CMD ["java","-jar","WebServerDemo-1.0-SNAPSHOT.jar"]