FROM openjdk:17
EXPOSE 8080:8080
RUN mkdir /app
COPY ./build/libs/*-all.jar /app/NekrasovSprint1-all.jar
ENTRYPOINT ["java","-jar","/app/NekrasovSprint1-all.jar"]