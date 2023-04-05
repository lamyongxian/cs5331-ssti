FROM tomcat:9.0
COPY . /vagrant
ENTRYPOINT ["java","-jar","/vagrant/youtrack/youtrack-2020.5.2579.jar", "--no-browser", "8080"]
#RUN java -jar /vagrant/youtrack/youtrack-2020.5.2579.jar --no-browser 8080/youtrack

#COPY /target/xxx.war /usr/local/tomcat/webapps/
#COPY /target/xxx.war /var/lib/tomcat9/webapps/
#COPY /tomcat/ /usr/local/tomcat/webapps/ROOT/

#ENTRYPOINT ["catalina.sh", "run"]
