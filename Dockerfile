FROM openjdk:14
COPY ../covid-simulation-automatic-deploy@2/target/covid-simulation-1.0-SNAPSHOT.jar /usr/local/covid-simulation-1.0-SNAPSHOT.jar
CMD java -jar /usr/local/covid-simulation-1.0-SNAPSHOT.jar
