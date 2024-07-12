#Base Image
FROM openjdk:17-jdk-alpine

#tambah jar file yang ada kedalam target image
ADD target/kelompok-0.0.1-SNAPSHOT.jar kelompok.jar

#Entry Point
CMD [ "java", "-jar", "/kelompok.jar" ]