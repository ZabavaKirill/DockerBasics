FROM jelastic/maven:3.9.9-openjdk-22.0.2-almalinux-9 AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
RUN mvn verify --fail-never
ADD . $HOME
RUN mvn -DskipTests package

FROM openjdk:24
COPY --from=build /usr/app/target/*.jar /app.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/app.jar"]