
FROM java:latest
ADD zskg-xianhezi-config-0.0.1.jar
RUN bash -c 'touch /config.jar'
ENTRYPOINT["java","-jar","/app.jar"]


#FROM centos
#MAINTAINER Yang <280885685@qq.com>
#ADD /data/xian62/zskg-xianhezi-config-0.0.1.jar
#ENV JAVA_HOME=/home/java
#ENV PATH=$JAVA_HOME/bin:$PATH
#ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
#ENTRYPOINT ["java","-jar","/zskg-xianhezi-config-0.0.1.jar"]
#EXPOSE 8888


