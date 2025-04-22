# Temel imaj (Java 17)
FROM eclipse-temurin:17-jdk-alpine

# Uygulama jar dosyası için isim
ARG JAR_FILE=target/hello-0.0.1-SNAPSHOT.jar

# Çalışma dizini
WORKDIR /app

# Jar dosyasını konteynıra kopyala
COPY ${JAR_FILE} app.jar

# Uygulama başlat
ENTRYPOINT ["java","-jar","app.jar"]
