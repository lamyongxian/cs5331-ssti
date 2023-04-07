FROM maven as maven
COPY ./CS5331SSTI /vagrant/CS5331SSTI
#WORKDIR  /CS5331SSTI/
RUN mvn -f /vagrant/CS5331SSTI/pom.xml clean package -DskipTests

FROM tomcat:9.0-jdk11-corretto
#COPY /target/CS5331SSTI.war /usr/local/tomcat/webapps/
COPY --from=maven /vagrant/CS5331SSTI/target/CS5331SSTI.war /usr/local/tomcat/webapps/ROOT.war
#COPY /tomcat/ /usr/local/tomcat/webapps/ROOT/
ENTRYPOINT ["catalina.sh", "run"]
