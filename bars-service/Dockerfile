FROM quay.io/wildfly/wildfly:latest

ARG WILDFLY_HOME=/opt/jboss/wildfly
ARG BARS_SERVICE_WAR=./bars-service/target/bars-service.war

COPY ${BARS_SERVICE_WAR} ${WILDFLY_HOME}/standalone/deployments
COPY ./bars-service/wildfly-config/bars-service-standalone.xml ${WILDFLY_HOME}/standalone/configuration/standalone.xml

ENTRYPOINT ["sh", "/opt/jboss/wildfly/bin/standalone.sh"]