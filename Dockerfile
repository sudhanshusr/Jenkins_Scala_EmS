FROM jamespedwards42/docker-ubuntu-java-8:latest
ADD /target/scala-2.10/firstApp-assembly-1.0-SNAPSHOT.jar /
CMD ["java","-jar","/firstApp-assembly-1.0-SNAPSHOT.jar"]
#CMD ["root/firstApp-assembly-1.0-SNAPSHOT.jar"]
#RUN java -jar /root/firstApp-assembly-1.0-SNAPSHOT.jar


