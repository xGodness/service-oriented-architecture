FROM quay.io/wildfly/wildfly:latest-jdk17

ARG WILDFLY_HOME=/opt/jboss/wildfly
ARG BARS_EJB_JAR=./bars-ejb/target/bars-ejb.jar

COPY ${BARS_EJB_JAR} ${WILDFLY_HOME}/standalone/deployments
COPY ./bars-ejb/wildfly-config/bars-ejb-standalone.xml ${WILDFLY_HOME}/standalone/configuration/standalone.xml
COPY ./bars-ejb/wildfly-config/wildfly-truststore.p12 ${WILDFLY_HOME}/standalone/configuration/wildfly-truststore.p12
COPY ./bars-ejb/wildfly-config/standalone.conf ${WILDFLY_HOME}/bin/standalone.conf

# user authentication is needed by remote ejb & jndi (see /standalone/configuration/application-users.properties)
RUN sh ${WILDFLY_HOME}/bin/add-user.sh -a -u user -p password

ENTRYPOINT ["sh", "/opt/jboss/wildfly/bin/standalone.sh"]