FROM jboss/wildfly


ARG WILDFLY_HOME=/opt/jboss/wildfly
ARG SUPERVISOR_HOME=/etc/supervisor
ARG BARS_EJB_JAR=./bars-ejb/target/bars-ejb.jar
ARG BARS_SERVICE_WAR=./bars-service/target/bars-service.war


RUN cp -r ${WILDFLY_HOME}/standalone ${WILDFLY_HOME}/standalone-bars-ejb

ADD ${BARS_EJB_JAR} ${WILDFLY_HOME}/standalone-bars-ejb/deployments
ADD ./wildfly/bars-ejb-standalone.xml ${WILDFLY_HOME}/standalone-bars-ejb/configuration/standalone.xml

ADD ${BARS_SERVICE_WAR} ${WILDFLY_HOME}/standalone-bars-ejb/deployments
ADD ./wildfly/bars-service-standalone.xml ${WILDFLY_HOME}/standalone/configuration/standalone.xml
ADD ./wildfly/wildfly-truststore.p12 ${WILDFLY_HOME}/standalone/configuration/application.keystore


USER root
COPY ./wildfly/wildfly-startup.sh wildfly-startup.sh
CMD chmod u+x ./wildfly-startup.sh && ./wildfly-startup.sh