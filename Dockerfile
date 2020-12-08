FROM maven:3.6.3-jdk-11

COPY src /src
COPY pom.xml pom.xml

EXPOSE 8080

RUN mvn clean package
RUN mvn verify -Pfailsafe
CMD java -Xms250m -Xmx1024m -jar /target/fizzbuzz-0.0.1-SNAPSHOT.jar