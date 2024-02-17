FROM eclipse-temurin:17

LABEL mentainer="wpghks56@gmail.com"

WORKDIR /app

COPY build/libs/owner-product-server-0.0.1-SNAPSHOT.jar /app/owner-product-server.jar

ENV SPRING_DATASOURCE_URL="jdbc:mysql://dbDocker:3306/mydatabase?serverTimezone=Asia/Seoul"
ENV SPRING_DATASOURCE_PASSWORD="secret"
ENV SPRING_DATASOURCE_USERNAME="myuser"
ENV SPRING_JWT_SECRET="sksmsgodqhrgkwlakstktlfrmrjt"
ENV SPRING_JWT_EXPIRED=86400

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "owner-product-server.jar"]